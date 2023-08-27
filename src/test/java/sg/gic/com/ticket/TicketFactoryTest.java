package sg.gic.com.ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.gic.com.utils.Constants.TICKET_NUM_NUMBERS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TicketFactoryTest {
  TicketFactory factory;

  @BeforeEach
  void setUp() {
    factory = new TicketFactory();
  }

  @Test
  @DisplayName("generate works as expected")
  void generate() {
    Ticket generatedTicket = factory.generate();

    assertEquals(Ticket.class, generatedTicket.getClass());
    assertEquals(TICKET_NUM_NUMBERS, generatedTicket.getTicketNumbers().size());
  }
}
