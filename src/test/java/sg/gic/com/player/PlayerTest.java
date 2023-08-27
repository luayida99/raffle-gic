package sg.gic.com.player;

import static org.junit.jupiter.api.Assertions.*;
import static sg.gic.com.utils.Constants.MAX_TICKETS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.gic.com.exceptions.MaxTicketsExceededException;
import sg.gic.com.exceptions.NegativeTicketsToBuyException;
import sg.gic.com.ticket.TicketFactory;
// TODO: Move tests to Draw
public class PlayerTest {
  Player player;

  /*
  @BeforeEach
  void setUp() {
    player = new Player("John");
  }

  @Test
  @DisplayName("buyTickets within limit works as expected")
  void buyTicketsWithinLimit() {
    player.buyTickets(1);

    assertEquals(1, player.getNumTickets());
  }

  @Test
  @DisplayName("buyTickets exceeding max amount throws MaxTicketsExceededException")
  void buyTicketsExceedLimit() {
    assertThrows(MaxTicketsExceededException.class, () -> player.buyTickets(MAX_TICKETS + 1));
  }

  @Test
  @DisplayName("buyTickets with negative numToBuy throws NegativeTicketsToBuyException")
  void buyTicketsNegative() {
    assertThrows(NegativeTicketsToBuyException.class, () -> player.buyTickets(-1));
  }

  @Test
  @DisplayName("ticketsToString works as expected")
  void ticketsToString() {
    player.buyTickets(MAX_TICKETS);
    String generatedString = player.ticketsToString();

    // Workaround for random
    for (int i = 1; i < MAX_TICKETS + 1; i++) {
      assertTrue(generatedString.contains("Ticket %d".formatted(i)));
    }
  }
  */
}
