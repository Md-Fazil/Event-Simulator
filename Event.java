package cs2030.simulator;

/** 
* Abstract event class for specific event classes to inherit from.
*/
public abstract class Event {
    protected final Customer customer;
    protected final double time;
    protected final Server server;

    Event(Customer customer, double time, Server server) {
        this.customer = customer;
        this.time = time;
        this.server = server;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public double getTime() {
        return this.time;
    }

    public Server getServer() {
        return this.server;
    }

    @Override
    public abstract String toString();

}








