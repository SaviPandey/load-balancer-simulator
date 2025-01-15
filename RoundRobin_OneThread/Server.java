package RoundRobin_OneThread;

public class Server {
    private int id;
    private String serverName;
    private boolean isBusy = false;

    public Server(int id) {
        this.id = id;
        this.serverName = "Server-"+id;
    }

    public void assignRequest(int requestNo) {
        System.out.println(serverName + " serving request " + requestNo);
        isBusy = true;
        try {
            // Do Work...
            Thread.sleep(1000*1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // System.out.println(serverName + " served request " + requestNo);
        isBusy = false;
    }

    public boolean isBusy() {
        return this.isBusy;
    }
}
