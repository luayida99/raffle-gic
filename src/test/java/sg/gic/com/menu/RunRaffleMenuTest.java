package sg.gic.com.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.gic.com.draw.Draw;
import sg.gic.com.mocks.MockTicketFactory;
import sg.gic.com.player.Player;
import sg.gic.com.raffle.Raffle;

public class RunRaffleMenuTest {
  private RunRaffleMenu menu;
  private MockTicketFactory factory;
  private Draw draw;
  private Raffle raffle;

  @BeforeEach
  void setUp() {
    factory = new MockTicketFactory();
    draw = new Draw(factory);
    raffle = new Raffle(draw, factory);
    menu = new RunRaffleMenu(raffle);
  }

  @Test
  @DisplayName("toString with empty run works as expected")
  void toStringEmptyRunTest() {
    raffle.run();

    String menuString = menu.toString();
    String expectedString =
        "Running Raffle..\n"
            + "Winning Ticket is 1 2 3 4 5\n"
            + "\n"
            + "Group 2 Winners: \n"
            + "Nil\n"
            + "\n"
            + "\n"
            + "Group 3 Winners: \n"
            + "Nil\n"
            + "\n"
            + "\n"
            + "Group 4 Winners: \n"
            + "Nil\n"
            + "\n"
            + "\n"
            + "Group 5 Winners (Jackpot): \n"
            + "Nil\n"
            + "\n"
            + "Press any key to return to main menu";

    assertEquals(expectedString, menuString);
  }

  @Test
  @DisplayName("toString with players works as expected")
  void toStringNonemptyTest() {
    Player john = draw.addPlayer(new Player("John"));
    draw.buyTickets(5, john);

    raffle.run();

    String menuString = menu.toString();
    String expectedString =
        "Running Raffle..\n"
            + "Winning Ticket is 1 2 3 4 5\n"
            + "\n"
            + "Group 2 Winners: \n"
            + "Nil\n"
            + "\n"
            + "\n"
            + "Group 3 Winners: \n"
            + "Nil\n"
            + "\n"
            + "\n"
            + "Group 4 Winners: \n"
            + "Nil\n"
            + "\n"
            + "\n"
            + "Group 5 Winners (Jackpot): \n"
            + "John with 5 winning ticket(s) - $62.50\n"
            + "\n"
            + "\n"
            + "Press any key to return to main menu";

    assertEquals(expectedString, menuString);
  }
}
