package WeightedRoundRobin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Server {
    private int id;
    private String serverName;
    private int weight;
    private ExecutorService executorService;
    public Server(int id, int weight) {
        this.id = id;
        this.serverName = "Server-"+id;
        this.weight = weight;
        this.executorService = Executors.newFixedThreadPool(weight);
    }

    public void assignRequest(int requestNo) {
        executorService.submit(() -> {
            System.out.println(serverName + " started processing request " + requestNo);
            try {
                 Thread.sleep(30000);
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
