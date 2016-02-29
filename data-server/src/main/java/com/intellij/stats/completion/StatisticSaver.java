package com.intellij.stats.completion;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StatisticSaver {

    private Map<String, ContentInfo> myIdToKb = new HashMap<>();
    
    public void dataReceived(String fromId, int contentLength) {
        double newContentKb = ((double) contentLength * 2) / 1024;
        ContentInfo info = myIdToKb.get(fromId);
        long firstSent = info == null ? System.currentTimeMillis() : info.firstSentTimestamp;
        double newSizeKb = info == null ? newContentKb : (info.receivedDataKb + newContentKb);
        myIdToKb.put(fromId, new ContentInfo(newSizeKb, firstSent));
    }
    
    public Collection<ContentInfo> getAllData() {
        return myIdToKb.values();
    }

    public Set<String> getAllUserIds() {
        return myIdToKb.keySet();
    }

    public ContentInfo getInfoFor(String uid) {
        return myIdToKb.get(uid);
    }

}


class ContentInfo {
    public final double receivedDataKb;
    public final long lastSentTimestamp;
    public final long firstSentTimestamp;

    public ContentInfo(double receivedDataKb, long firstTimestamp) {
        this.receivedDataKb = receivedDataKb;
        this.firstSentTimestamp = firstTimestamp;
        this.lastSentTimestamp = System.currentTimeMillis();
    }
}
