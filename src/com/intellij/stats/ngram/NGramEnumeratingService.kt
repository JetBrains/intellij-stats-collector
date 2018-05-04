package com.intellij.stats.ngram

import com.intellij.openapi.Disposable
import com.intellij.openapi.application.PathManager
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.io.FileUtilRt
import com.intellij.util.containers.ContainerUtil
import com.intellij.util.io.PagedFileStorage
import com.intellij.util.io.PersistentEnumeratorBase
import com.intellij.util.io.PersistentStringEnumerator
import java.io.File


class NGramEnumeratingService : Disposable {
    private val LOG = logger<NGramFileBasedIndex>()
    private val stringFile = File(File(PathManager.getIndexRoot(), KEY), "strings")

    private val stringEnumerator: PersistentStringEnumerator

    private val stringCache = ContainerUtil.createConcurrentSoftMap<String, Int>()
    private val storageLockContext = PagedFileStorage.StorageLockContext(true)

    init {
        FileUtilRt.createIfNotExists(stringFile)
        stringEnumerator = try {
            PersistentStringEnumerator(stringFile, storageLockContext)
        } catch (e: PersistentEnumeratorBase.CorruptedException) {
            LOG.warn("String enumeration file was corrupted. Creating a new file and restarting ngram indexing...")
            FileUtilRt.delete(stringFile.parentFile)
            FileUtilRt.createIfNotExists(stringFile)
            NGramFileBasedIndex.requestRebuild()
            PersistentStringEnumerator(stringFile, storageLockContext)
        }

    }

    fun enumerateString(s: String): Int {
        return stringCache.computeIfAbsent(s, { enumerateStringImpl(it) })
    }

    fun valueOf(index: Int): String = runWithLock {
        stringEnumerator.valueOf(index) ?: throw IllegalArgumentException(index.toString())
    }

    private fun enumerateStringImpl(s: String): Int = runWithLock { stringEnumerator.enumerate(s) }

    override fun dispose() {
        runWithLock { stringEnumerator.close() }
    }

    private fun <T> runWithLock(action: () -> T): T {
        try {
            storageLockContext.lock()
            return action()
        } finally {
            storageLockContext.unlock()
        }
    }

    companion object {
        const val KEY = "ngram.stringEnumerator"
        fun getInstance(): NGramEnumeratingService = ServiceManager.getService(NGramEnumeratingService::class.java)
    }
}
