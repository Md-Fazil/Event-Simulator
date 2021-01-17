package cs2030.simulator;

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;

/*
 * Class manages the different events of customers from their arrival till their departure.
 */
public class EventManager {

    public static RandomGenerator setup(int seed, double arrival, double service, double rest) {
        return new RandomGenerator(seed, arrival, service, rest);
    }

    /**
     * Creates a PriorityQueue of arrival event of customers who are scheduled to arrive.
     * @param random randomgenerator to generate random values.
     * @param numOfCustomers number of customers who will be arriving.
     * @param pGreedy probability a customer is greedy.
     * @return PriorityQueue of arrival events of each arriving customer.
     */
    public static PriorityQueue<Event> createEvent(RandomGenerator random, 
        int numOfCustomers, double pGreedy) {
        PriorityQueue<Event> queue = new PriorityQueue<>(1, new Compare());
        ArrayList<Double> times = new ArrayList<>();
        double init = 0.00;
        times.add(init);
        for (int i = 1; i < numOfCustomers; i++) {
            init = init + random.genInterArrivalTime();
            times.add(init);
        }
        Collections.sort(times);
        for (int i = 1; i <= numOfCustomers; i++) {
            if (random.genCustomerType() < pGreedy) {
                queue.add(new ArrivalEvent(new Customer(i, times.get(i - 1), Customer.greedy)));
            } else {
                queue.add(new ArrivalEvent(new Customer(i, times.get(i - 1), Customer.typical))); 
            }
        }
        return queue;
    }

    /**
     * Manages the schedule of events according to their priority.
     * @param random randomgenerator to provide random values.
     * @param numOfServers number of human servers in charge of serving the customers.
     * @param numOfCustomers number of customers who will be arriving.
     * @param numOfCheckout number of checkout counters to serve the customers.
     * @param queueLength maximum queue length for servers, both human and self-checkout.
     * @param pGreedy - probability a customer is greedy.
     * @param pRest - probability of a server resting after serving a customer.  
     */
    public static void manage(RandomGenerator random, int numOfServers, int numOfCustomers, 
        int numOfCheckout, int queueLength, double pGreedy, double pRest) {           
        PriorityQueue<Event> queue = EventManager.createEvent(random, numOfCustomers, pGreedy);
        ServerManager serverManager = new ServerManager(numOfServers, numOfCheckout, 
            queueLength, pRest);
        while (queue.peek() != null) {
            Event currentEvent = queue.poll();
            Customer currentCustomer = currentEvent.getCustomer();
            double currentTime = currentEvent.getTime();
            Server currentServer = currentEvent.getServer();        
            if (currentEvent instanceof ServerBreak) {
                queue.add(new ServerBack(currentServer, currentTime));
            } else if (currentEvent instanceof ServerBack) {
                serverManager.notifyServerBack(currentServer);
                Customer nextCustomer = currentServer.serveWaiting();
                if (nextCustomer != null) {
                    Event currentServe = new ServeEvent(nextCustomer, currentTime, currentServer);
                    queue.add(currentServe);
                    Statistics.customerWaits(currentServe, nextCustomer);
                }
            } else { 
                System.out.println(currentEvent.toString());
                if (currentEvent instanceof ArrivalEvent) {
                    Server freeServer = serverManager.assignServer(currentCustomer);
                    if (freeServer == null) {
                        queue.add(new LeaveEvent(currentCustomer, currentTime));
                        Statistics.customerLeft();
                    } else if (freeServer.isServing() != true && freeServer.isResting() != true) {
                        queue.add(new ServeEvent(currentCustomer, currentTime, freeServer));
                    } else {
                        freeServer.serveLater(currentCustomer);
                        queue.add(new WaitEvent(currentCustomer, 
                            currentTime, freeServer));
                    } 
                } else if (currentEvent instanceof ServeEvent) {
                    currentServer.serve(currentCustomer);
                    queue.add(new DoneEvent(currentCustomer, 
                        currentTime + random.genServiceTime(), currentServer));
                    Statistics.customerServed();
                } else if (currentEvent instanceof DoneEvent) {
                    currentServer.doneServing();
                    if (currentServer.getType() == Server.Human && 
                        random.genRandomRest() < pRest) {
                        queue.add(new ServerBreak(currentServer, 
                            currentTime + random.genRestPeriod()));
                        serverManager.notifyServerRest(currentServer);
                    } else {
                        queue.add(new ServerBack(currentServer, currentTime));
                    }
                }
            }
        }   
    }       
}







                  

       




  
