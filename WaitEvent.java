package cs2030.simulator;

/** 
 * Class responsible for the creation of wait events.
 */
public class WaitEvent extends Event {
   
    WaitEvent(Customer c, double time, Server server) {
        super(c, time, server);
    }
    
    @Override
    public String toString() {
        return String.format("%.3f ", this.customer.getArrivalTime()) + 
                this.customer + " waits to be served by " + server;
    }
}
