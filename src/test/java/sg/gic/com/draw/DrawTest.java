package sg.gic.com.draw;

import static org.junit.jupiter.api.Assertions.*;
import static sg.gic.com.utils.Constants.MAX_TICKETS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.gic.com.exceptions.MaxTicketsExceededException;
import sg.gic.com.exceptions.NegativeTicketsToBuyException;
import sg.gic.com.mocks.MockTicketFactory;
import sg.gic.com.player.Player;

public class DrawTest {

  private Player player;
  private Draw draw;

  @BeforeEach
  void setUp() {
    player = new Player("John");
    draw = new Draw(new MockTicketFactory());
  }

  @Test
  @DisplayName("buyTickets within limit works as expected")
  void buyTicketsWithinLimit() {
    draw.buyTickets(1, player);

    assertEquals(1, player.getNumTickets());
    assertEquals("Ticket 1: 1 2 3 4 5", player.ticketsToString());
  }

  @Test
  @DisplayName("buyTickets exceeding max amount throws MaxTicketsExceededException")
  void buyTicketsExceedLimit() {
    assertThrows(MaxTicketsExceededException.class, () -> draw.buyTickets(MAX_TICKETS + 1, player));
  }

  @Test
  @DisplayName("buyTickets with negative numToBuy throws NegativeTicketsToBuyException")
  void buyTicketsNegative() {
    assertThrows(NegativeTicketsToBuyException.class, () -> draw.buyTickets(-1, player));
  }

  @Test
  @DisplayName("addPlayer works as expected")
  void addPlayer() {
    draw.addPlayer(new Player("John"));
    draw.addPlayer(new Player("Jane"));

    assertEquals(2, draw.getPlayers().size());
    for (Player play : draw.getPlayers()) {
      assertEquals(Player.class, play.getClass());
    }
  }
}
