package cs2030.simulator;

/**
 * Class responsible for the creation of ServerBack events when servers return from break.
 */
public class ServerBack extends Event {

    ServerBack(Server server, double time) {
        super(null, time, server);
    }
  
    @Override
    public String toString() {
        return "";
    }

}