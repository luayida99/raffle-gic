package sg.gic.com.menu;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static sg.gic.com.utils.Constants.JACKPOT_CATEGORY;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.gic.com.draw.Draw;
import sg.gic.com.raffle.Raffle;
import sg.gic.com.ticket.TicketFactory;

public class RunRaffleMenuTest {
  private RunRaffleMenu menu;

  @BeforeEach
  void setUp() {
    TicketFactory factory = new TicketFactory();
    Raffle raffle = new Raffle(new Draw(factory), factory);
    menu = new RunRaffleMenu(raffle);
    raffle.run();
  }

  @Test
  @DisplayName("toString works as expected")
  void toStringTest() {
    String menuString = menu.toString();
    assertTrue(menuString.contains("Running Raffle..\n"));
    for (int i = 2; i < JACKPOT_CATEGORY; i++) {
      assertTrue(menuString.contains("Group %d Winners: ".formatted(i)));
    }
    assertTrue(menuString.contains("Group 5 Winners (Jackpot):"));
    assertTrue(menuString.contains("Press any key to return to main menu"));
  }
}
