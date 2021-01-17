package cs2030.simulator;


/** 
 * Class responsible for the creation of done events.
 */
public class DoneEvent extends Event {
 
    DoneEvent(Customer current, double time, Server server) {
        super(current, time, server);   
    }

    @Override
    public String toString() { 
        return String.format("%.3f ", super.getTime()) 
            + this.customer + " done serving by " + this.server;
    }
}




