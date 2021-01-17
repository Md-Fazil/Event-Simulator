package cs2030.simulator;

/** 
 * Class responsible for the creation of leave events.
 */
public class LeaveEvent extends Event {

    LeaveEvent(Customer current, double time) {
        super(current, time, null);
    }

    @Override
    public String toString() {
        return String.format("%.3f ", super.getTime()) + this.customer + " leaves";
    }
}
