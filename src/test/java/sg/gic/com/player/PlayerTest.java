package sg.gic.com.player;

import static org.junit.jupiter.api.Assertions.*;
import static sg.gic.com.utils.Constants.MAX_TICKETS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.gic.com.draw.Draw;
import sg.gic.com.exceptions.MaxTicketsExceededException;
import sg.gic.com.exceptions.NegativeTicketsToBuyException;
import sg.gic.com.ticket.TicketFactory;
// TODO: Move tests to Draw
public class PlayerTest {
  private Player player;
  private Draw draw;

  @BeforeEach
  void setUp() {
    player = new Player("John");
    draw = new Draw(new TicketFactory());
  }

  @Test
  @DisplayName("ticketsToString works as expected")
  void ticketsToString() {
    draw.buyTickets(MAX_TICKETS, player);
    String generatedString = player.ticketsToString();

    // Workaround for random
    for (int i = 1; i < MAX_TICKETS + 1; i++) {
      assertTrue(generatedString.contains("Ticket %d".formatted(i)));
    }
  }
}
