package WeightedRoundRobin;
import java.util.Scanner;
public class Main {
    private static volatile boolean running = true;
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the Number of Servers: ");
        int noOfServers = scan.nextInt();
        LoadBalancer loadBalancer = new LoadBalancer(noOfServers);


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nCtrl + C detected. Stopping the program...");
            running = false;
        }));

        System.out.println("Enter request numbers to assign them to servers. Press Ctrl + C to exit.");

        while (running) {
            try {
                System.out.print("RequestNo: ");
                int requestNo = scan.nextInt();
                loadBalancer.handleRequest(requestNo);
            } catch (Exception e) {
                if (!running) {
                    break;
                }
                System.out.println("Invalid input. Please enter a valid request number.");
                scan.next();
            }
        }

        System.out.println("Program terminated.");
        scan.close();
    }
}
