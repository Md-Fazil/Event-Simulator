package cs2030.simulator;

import java.util.Comparator;

public class Compare implements Comparator<Event> {

    /**
     * Compares the priority of 2 events using their respective time and customer ID.     
     * @param e1 first event to be compared.
     * @param e2 second event to be compared.
     * @return negative integer if the first event is of higher priority than second event,
     *         positive integer if vice versa and zero otherwise.
     */
    public int compare(Event e1, Event e2) {
        if (e1 == null) {
            return 1;
        } else if (e2 == null) {
            return -1;
        } else {
            Customer c1 = e1.getCustomer();
            Customer c2 = e2.getCustomer();
            if (e1.getTime() < e2.getTime()) {
                return -1;
            } else if (e1.getTime() > e2.getTime()) {
                return 1;
            } else if (c1 == null && c2 == null) {
                return -1;
            } else if (c1 == null || c2 == null) {
                return -1;
            } else if (c1.getID() < c2.getID()) {
                return -2;
            } else if (c1.getID() > c2.getID()) {
                return 2;
            } else {
                return 0;
            }
        }

    }   

}

        
