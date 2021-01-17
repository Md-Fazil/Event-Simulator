package cs2030.simulator;

import java.lang.Comparable;

/**
 * Class responsible for the creation of greedy and typical customers.
 */
public class Customer implements Comparable<Customer> {
    private final int id;
    private final double arrivalTime;
    private final int customerType;
    public static final int typical = 0;
    public static final int greedy = 1; 
   
    Customer(int id, double arrivalTime, int customerType) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.customerType = customerType;
    }

    public int getID() { 
        return this.id;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public int getCustomerType() {
        return customerType;
    }

    /**
     * Compares the ID of a customer with another customer's ID.
     * @param otherCustomer customer whose ID will be compared with.
     * @return negative integer if the current customer has a smaller ID than the other customer,
     *         positive integer if vice versa and zero otherwise. 
     */
    @Override
    public int compareTo(Customer otherCustomer) {
        if (this.id < otherCustomer.getID()) {
            return -1;
        } else if (this.id > otherCustomer.getID()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        if (customerType == typical) {
            return Integer.toString(this.id);
        } else {
            return Integer.toString(this.id) + "(greedy)"; 
        }
    }

}
