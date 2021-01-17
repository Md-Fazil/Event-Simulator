package cs2030.simulator;

import java.util.PriorityQueue;

/**
 * Class responsible for the creation of human servers and self-checkout counters.
 */
public class Server {
    private final int id;
    private Customer currentlyServing;
    private PriorityQueue<Customer> waitQueue;
    private boolean isResting;
    private final int maxQueueLength;
    private final int serverType;
    private final double probabilityOfResting;
    public static final int Counter = 0;
    public static final int Human = 1; 


    private Server(int id, int maxQueueLength, double probabilityOfResting) {
        this.id = id;
        this.currentlyServing = null;
        this.waitQueue = new PriorityQueue<Customer>(maxQueueLength);
        this.isResting = false;
        this.maxQueueLength = maxQueueLength;
        this.serverType = Server.Human;
        this.probabilityOfResting = probabilityOfResting;
    }
    
    private Server(int id, PriorityQueue<Customer> queue, int maxQueueLength) {
        this.id = id;
        this.currentlyServing = null;
        this.waitQueue = queue;
        this.isResting = false;
        this.maxQueueLength = maxQueueLength;
        this.serverType = Server.Counter;
        this.probabilityOfResting = 0.0;
    }

    public static Server createHumanServer(int id, int maxQueueLength, double probability) {
        return new Server(id, maxQueueLength, probability);
    }

    public static Server createCheckoutCounter(int id, int maxWait, PriorityQueue<Customer> queue) {
        return new Server(id, queue, maxWait);
    }
    
    public int getType() {
        return this.serverType;
    }

    public int getQueueSize() {
        return this.waitQueue.size();
    }

    public double getProbability() {
        return this.probabilityOfResting;
    }

    public boolean isServing() {
        return currentlyServing != null;
    }

    public boolean isResting() {
        return this.isResting;
    }

    public boolean canServeLater() {
        return waitQueue.size() < maxQueueLength;
    }

    /**
     * Changes server's state to resting and server will not be able to serve any customer.
     */
    public void rest() {
        if (this.serverType == Human) {
            this.isResting = true;
        }
    }

    /**
     * Changes server's state to not resting and server will be able to serve a customer.
     */
    public void back() {
        this.isResting = false;
    }
    

    /**
     * Adds a customer to the server's queue. 
     * @param waitingCustomer customer who will wait in the queue for the server to serve later.
     */
    public void serveLater(Customer waitingCustomer) {
        this.waitQueue.add(waitingCustomer);
    }
    

    /**
     * Assigns the server to serve a given customer.
     * @param customer customer to be served by the Server.
     */
    public void serve(Customer customer) {
        if (waitQueue.contains(customer)) {
            waitQueue.remove(customer);
            this.currentlyServing = customer;
        } else {
            this.currentlyServing = customer;
        }
    }

    /**
     * Returns the next customer in the queue for the server to serve if any.
     * @return Customer customer for the server to serve next.
     */
    public Customer serveWaiting() {
        Customer next = waitQueue.poll();
        if (next != null) {
            currentlyServing = next;
            return next;
        } else {
            return null;
        }
    }

    public void doneServing() {
        this.currentlyServing = null;
    }

    @Override
    public String toString() {
        if (this.serverType == Server.Human) {
            return "server " + Integer.toString(this.id);
        } else {
            return "self-check " + Integer.toString(this.id);
        }
    }

}

