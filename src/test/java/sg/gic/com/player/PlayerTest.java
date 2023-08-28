package sg.gic.com.player;

import static org.junit.jupiter.api.Assertions.*;
import static sg.gic.com.utils.Constants.MAX_TICKETS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.gic.com.draw.Draw;
import sg.gic.com.mocks.MockTicketFactory;

public class PlayerTest {
  private Player player;
  private Draw draw;

  @BeforeEach
  void setUp() {
    player = new Player("John");
    draw = new Draw(new MockTicketFactory());
  }

  @Test
  @DisplayName("matches works as expected")
  void matchesTest() {
    Player p1 = new Player("john");
    Player p1Duplicate = new Player("john");
    Player p2 = new Player("jane");

    assertTrue(p1.matches(p1Duplicate));
    assertFalse(p1.matches(p2));
  }

  @Test
  @DisplayName("ticketsToString works as expected")
  void ticketsToString() {
    draw.buyTickets(MAX_TICKETS, player);
    String generatedString = player.ticketsToString();
    String expectedString =
        "Ticket 1: 1 2 3 4 5\n"
            + "Ticket 2: 1 2 3 4 5\n"
            + "Ticket 3: 1 2 3 4 5\n"
            + "Ticket 4: 1 2 3 4 5\n"
            + "Ticket 5: 1 2 3 4 5";

    assertEquals(expectedString, generatedString);
  }
}
