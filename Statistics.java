package cs2030.simulator;

/*
 * Class handles the tracking of number of customers served and 
 * left as well as average waiting time for each customer.
 */
public class Statistics {

    private static int customersServed = 0;
    private static int customersLeft = 0;
    private static double totalWaitingTime = 0.0;


    public static void customerServed() {
        Statistics.customersServed = customersServed + 1;
    }

    public static void customerLeft() {
        Statistics.customersLeft = Statistics.customersLeft + 1;
    }

    /** 
    * Keeps track of the total wait time for all the customers who waited.
    * @param event waitEvent of customer who is scheduled to be served later.
    * @param customer customer who is waiting.
    */
    public static void customerWaits(Event event, Customer customer) {
        Statistics.totalWaitingTime = totalWaitingTime + 
            (event.getTime() - customer.getArrivalTime());
    }

    /**
     * Prints the required statistics(average waiting time, number of customers served 
     * and number of customers who left).
     */
    public static void output() {
        String averageTime;
        if (customersServed == 0) {
            averageTime = String.format("%.3f", 0.000);
        } else {
            averageTime = String.format("%.3f", totalWaitingTime / customersServed);
        }
        System.out.println("[" + averageTime + " " + customersServed + " " + customersLeft + "]");
    }

}
