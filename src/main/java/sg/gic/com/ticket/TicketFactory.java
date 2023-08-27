package sg.gic.com.ticket;

import static sg.gic.com.utils.Constants.*;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/** Factory for creating tickets. */
public class TicketFactory {
  /**
   * Generates a Ticket with 5 random numbers.
   *
   * @return Ticket containing 5 random numbers in ticketNumbers.
   */
  public Ticket generate() {
    ArrayList<Integer> numbers = new ArrayList<>();
    ThreadLocalRandom.current()
        .ints(TICKET_LOWER_BOUND, TICKET_UPPER_BOUND)
        .distinct()
        .limit(TICKET_NUM_NUMBERS)
        .forEach(numbers::add);
    return new Ticket(numbers.toArray(new Integer[0]));
  }
}
