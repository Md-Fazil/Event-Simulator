package cs2030.simulator;

/** 
 * Class responsible for the creation of serve events.
 */
public class ServeEvent extends Event {

    ServeEvent(Customer current, double time, Server server) {
        super(current, time, server);
       
    }

    @Override
    public String toString() { 
        return String.format("%.3f ", super.getTime()) 
            + this.customer + " served by " + this.server;
    }
}




