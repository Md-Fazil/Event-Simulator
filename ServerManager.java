package cs2030.simulator;

import java.util.PriorityQueue;

/**
 * Class responsible for creation and assignment of servers to customers.
 */
public class ServerManager {
    private final Server[] servers;

    ServerManager(int numOfServers, int numOfCheckout, int maxQueueLength, double probability) {
        Server[] servers = new Server[numOfServers + numOfCheckout];
        PriorityQueue<Customer> checkoutQueue = new PriorityQueue<>(maxQueueLength);
        for (int i = 0; i < numOfServers + numOfCheckout; i++) {
            if (i < numOfServers) {
                servers[i] = Server.createHumanServer(i + 1, maxQueueLength, probability);
            } else {
                servers[i] = Server.createCheckoutCounter(i + 1, maxQueueLength, checkoutQueue);    
            }
        }
        this.servers = servers;
    }
    
    public void notifyServerRest(Server server) {
        server.rest();
    }   
     
    public void notifyServerBack(Server server) {
        server.back();
    }
    
    /**
     * Determines the server to serve a given customer depending on their availability.
     * @param customer customer who is to be served.
     * @return Server server if anyone is available to serve and null otherwise.
     */
    public Server assignServer(Customer customer) {
        int numOfServers = servers.length;
        int count = 0;
        Server freeServer = null;
        for (int i = 0; i < numOfServers; i++) {
            if (!servers[i].isServing() && !servers[i].isResting() && count == 0) {
                freeServer = servers[i];
                count++;
            }
        }
        if (freeServer == null) {
            for (int i = 0; i < numOfServers; i++) {
                if (servers[i].canServeLater() && count == 0) {
                    freeServer = servers[i];
                    count++;
                } else if (customer.getCustomerType() == Customer.greedy && count != 0) {
                    if (servers[i].canServeLater() && servers[i].getQueueSize() 
                        < freeServer.getQueueSize()) {
                        freeServer = servers[i];
                    }
                }
            }
        }
        return freeServer;
    }
}


