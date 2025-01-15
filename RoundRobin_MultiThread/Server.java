package RoundRobin_MultiThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Server {
    private int id;
    private String serverName;
    private ExecutorService executorService;
    public Server(int id) {
        this.id = id;
        this.serverName = "Server-"+id;
        this.executorService = Executors.newFixedThreadPool(2);
    }

    public void assignRequest(int requestNo) {
        executorService.submit(() -> {
            System.out.println(serverName + " started processing request " + requestNo);
            try {
                 Thread.sleep(9000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(serverName + " interrupted while processing request " + requestNo);
            }
            System.out.println(serverName + " finished processing request " + requestNo);
        });
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
