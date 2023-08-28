package sg.gic.com.ticket;

import static sg.gic.com.utils.Constants.*;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/** Factory for creating tickets. */
public class TicketFactory {
  /**
   * Generates a Ticket with TICKET_NUM_NUMBERS random numbers between TICKET_LOWER_BOUND and
   * TICKET_UPPER_BOUND.
   *
   * @return Ticket containing TICKET_NUM_NUMBERS random numbers.
   */
  public Ticket generate() {
    ArrayList<Integer> numbers = new ArrayList<>();
    ThreadLocalRandom.current()
        .ints(TICKET_LOWER_BOUND, TICKET_UPPER_BOUND + 1) // upper bound is exclusive
        .distinct()
        .limit(TICKET_NUM_NUMBERS)
        .forEach(numbers::add);
    return new Ticket(numbers.toArray(new Integer[0]));
  }
}
