import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class LoaderTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2000);

        for (int i = 0; i < 11000; i++) {
            service.submit(LoaderTest::runRequestor);    
        }

        try {
            service.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    public static volatile AtomicInteger atomicFinished = new AtomicInteger();

    private static void runRequestor() {
        UUID uuid = UUID.randomUUID();
        Requestor requestor = new Requestor(uuid.toString());
        long start = System.currentTimeMillis();
        String response = requestor.request();
        long end = System.currentTimeMillis();
        int finished = LoaderTest.atomicFinished.incrementAndGet();
        System.out.println("Response took: " + (end - start) + "ms " + response + " finished: " + finished);
    }

}


class Requestor {
    private static final String URI = "http://unit-617.labs.intellij.net:8090/experiment/info/";
    private final String uuid;

    public Requestor(String uuid) {
        this.uuid = uuid;
    }

    public String request() {
        try {
            Response response = Request.Get(URI + uuid).execute();
            return response.returnContent().asString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
