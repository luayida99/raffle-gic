package sg.gic.com.ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TicketTest {
  private Ticket sortedTicket;
  private Ticket notSortedTicket;

  private String sortedTicketString;

  @BeforeEach
  void setUp() {
    sortedTicket = new Ticket(1, 2, 3, 4, 5);
    notSortedTicket = new Ticket(5, 4, 2, 3, 1);

    sortedTicketString = "1 2 3 4 5";
  }

  @Test
  @DisplayName("Sorted ticket toString works as expected")
  void toStringSortedTicket() {
    assertEquals(sortedTicketString, sortedTicket.toString());
  }

  @Test
  @DisplayName("Unsorted ticket gets sorted and toString works as expected")
  void toStringNotSortedTicket() {
    assertEquals(sortedTicketString, notSortedTicket.toString());
  }
}
