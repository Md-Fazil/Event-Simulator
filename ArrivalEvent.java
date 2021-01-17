package cs2030.simulator;

/**
 * Class responsible for the creation of arrival events.
 */
public class ArrivalEvent extends Event {

    ArrivalEvent(Customer current) {
        super(current, current.getArrivalTime(), null);
    }
   
    public String toString() {
        return String.format("%.3f ", this.getTime()) + this.customer + " arrives";
    }
}



