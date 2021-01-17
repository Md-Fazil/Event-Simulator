package cs2030.simulator;

/**
 * Class responsible for the creation of ServerBreak events when servers take a break.
 */
public class ServerBreak extends Event {

    ServerBreak(Server server, double time) {
        super(null, time, server);
    }

    @Override
    public String toString() {
        return "";
    }

}