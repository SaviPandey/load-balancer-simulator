package LeastConnections;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Server {
    private int id;
    private String serverName;
    private int activeConnections;
    private ExecutorService executorService;
    public Server(int id) {
        this.id = id;
        this.serverName = "Server-"+id;
        this.activeConnections = 0;
        this.executorService = Executors.newFixedThreadPool(10);
    }

    public void assignRequest(int requestNo) {
        executorService.submit(() -> {
            this.activeConnections++;
            System.out.println(serverName + " started processing request " + requestNo);
            try {
                 Thread.sleep(requestNo * 10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(serverName + " interrupted while processing request " + requestNo);
            }
            System.out.println(serverName + " finished processing request " + requestNo);
            this.activeConnections--;
        });
    }

    public int getActiveConnections() {
        return this.activeConnections;
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
