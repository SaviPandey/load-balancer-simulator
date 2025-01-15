package LeastConnections;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LoadBalancer {
    private int noOfServers;

    private List<Server> serverList;

    LoadBalancer(int noOfServers) {
        Scanner scan = new Scanner(System.in);
        this.noOfServers = noOfServers;
        this.serverList = new ArrayList<Server>();
        for(int i = 0; i < noOfServers; i++) {
            serverList.add(new Server(i+1));
        }
    }

    public void handleRequest(int requestId) {
        if (noOfServers == 0) {
            System.out.println("No servers available to handle the request: " + requestId);
            return;
        }

        Server leastLoadedServer = serverList.stream()
                .min((s1, s2) -> Integer.compare(s1.getActiveConnections(), s2.getActiveConnections()))
                .orElse(null);
        leastLoadedServer.assignRequest(requestId);
    }

    public void shutdownAllServers() {
        for (Server server : serverList) {
            server.shutdown();
        }
    }
}
