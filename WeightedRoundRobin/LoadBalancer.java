package WeightedRoundRobin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LoadBalancer {
    private int noOfServers;
    private int currIndex;
    private List<Server> serverList;

    LoadBalancer(int noOfServers) {
        Scanner scan = new Scanner(System.in);
        this.noOfServers = noOfServers;
        this.serverList = new ArrayList<Server>();
        for(int i = 0; i < noOfServers; i++) {
            System.out.println("Enter the Weight of " + (i+1) + "th server");
            int weight = scan.nextInt();
            serverList.add(new Server(i+1, weight));
        }
        currIndex = 0;
    }

    public void handleRequest(int requestId) {
        if (noOfServers == 0) {
            System.out.println("No servers available to handle the request: " + requestId);
            return;
        }

        serverList.get(currIndex).assignRequest(requestId);

        currIndex = (currIndex + 1) % noOfServers;
    }

    public void shutdownAllServers() {
        for (Server server : serverList) {
            server.shutdown();
        }
    }
}
