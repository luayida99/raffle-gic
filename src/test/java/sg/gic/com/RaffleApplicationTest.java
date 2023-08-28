package sg.gic.com;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static sg.gic.com.utils.Constants.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.gic.com.draw.Draw;
import sg.gic.com.menu.BuyTicketsPromptMenu;
import sg.gic.com.menu.MainMenu;
import sg.gic.com.menu.PurchasedTicketsMenu;
import sg.gic.com.menu.RunRaffleMenu;
import sg.gic.com.mocks.MockTicketFactory;
import sg.gic.com.player.Player;
import sg.gic.com.raffle.Raffle;

public class RaffleApplicationTest {
  private RaffleApplication raffleApplication;
  private MockTicketFactory factory;
  private Draw draw;
  private Raffle raffle;

  private InputStream in;
  private ByteArrayOutputStream out;

  private void setUpIO(String input) {
    in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    System.setIn(in);
    out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
  }

  @BeforeEach
  void setUp() {
    factory = new MockTicketFactory();
    draw = new Draw(factory);
    raffle = new Raffle(draw, factory);
    raffleApplication = new RaffleApplication(factory, draw, raffle);
  }

  @Test
  @DisplayName("Wrong input works as expected")
  void wrongInputTest() {
    String input = "4\nexit";

    setUpIO(input);
    raffleApplication.run();

    String outputString = out.toString();

    assertTrue(outputString.contains(new MainMenu().toString()));
    assertTrue(outputString.contains(WRONG_INPUT));
  }

  @Test
  @DisplayName("Starting a draw works as expected")
  void startDrawTest() {
    String input = "1" + System.lineSeparator() + "d" + System.lineSeparator() + "exit";

    setUpIO(input);
    raffleApplication.run();

    String outputString = out.toString();

    assertTrue(outputString.contains(new MainMenu().toString()));
    assertTrue(outputString.contains(EXIT_APP));
  }

  @Test
  @DisplayName("Buying tickets before draw starts fails")
  void buyTicketsBeforeDraw() {
    String input = "2" + System.lineSeparator() + "d" + System.lineSeparator() + "exit";

    setUpIO(input);
    raffleApplication.run();

    String outputString = out.toString();

    assertTrue(outputString.contains(new MainMenu().toString()));
    assertTrue(outputString.contains(DRAW_NOT_ONGOING));
  }

  @Test
  @DisplayName("Buying tickets after draw, within ticket limits works as expected")
  void buyTicketsAfterDrawWithinLimit() {
    String input =
        "1"
            + System.lineSeparator()
            + "d"
            + System.lineSeparator()
            + "2"
            + System.lineSeparator()
            + "test,3"
            + System.lineSeparator()
            + "exit";

    setUpIO(input);
    raffleApplication.run();

    String outputString = out.toString();

    assertTrue(outputString.contains(new MainMenu().toString()));
    assertTrue(outputString.contains(new BuyTicketsPromptMenu().toString()));
    assertTrue(outputString.contains(new PurchasedTicketsMenu(new Player("test"), 3).toString()));
  }

  @Test
  @DisplayName("Buying tickets after draw, beyond ticket limits works as expected")
  void buyTicketsAfterDrawBeyondLimit() {
    String input =
        "1"
            + System.lineSeparator()
            + "d"
            + System.lineSeparator()
            + "2"
            + System.lineSeparator()
            + "test,6"
            + System.lineSeparator()
            + "exit";

    setUpIO(input);
    raffleApplication.run();

    String outputString = out.toString();

    assertTrue(outputString.contains(new MainMenu().toString()));
    assertTrue(outputString.contains(new BuyTicketsPromptMenu().toString()));
    assertTrue(outputString.contains(MAX_TICKETS_EXCEEDED_EXCEPTION));
  }

  @Test
  @DisplayName("Buying tickets after draw, negative tickets works as expected")
  void buyTicketsAfterDrawNegativeTickets() {
    String input =
        "1"
            + System.lineSeparator()
            + "d"
            + System.lineSeparator()
            + "2"
            + System.lineSeparator()
            + "test,-1"
            + System.lineSeparator()
            + "exit";

    setUpIO(input);
    raffleApplication.run();

    String outputString = out.toString();

    assertTrue(outputString.contains(new MainMenu().toString()));
    assertTrue(outputString.contains(new BuyTicketsPromptMenu().toString()));
    assertTrue(outputString.contains(NEGATIVE_TICKETS_EXCEPTION));
  }

  @Test
  @DisplayName("Buying tickets with malformed input fails")
  void buyTicketsMalformedInput() {
    String input =
        "1"
            + System.lineSeparator()
            + "d"
            + System.lineSeparator()
            + "2"
            + System.lineSeparator()
            + "test,1.5"
            + System.lineSeparator()
            + "exit";

    setUpIO(input);
    raffleApplication.run();

    String outputString = out.toString();

    assertTrue(outputString.contains(new MainMenu().toString()));
    assertTrue(outputString.contains(new BuyTicketsPromptMenu().toString()));
    assertTrue(outputString.contains(NON_INTEGER_TICKETS_PURCHASE));
  }

  @Test
  @DisplayName("Starting raffle before draw starts fails")
  void startRaffleBeforeDraw() {
    String input = "3" + System.lineSeparator() + "d" + System.lineSeparator() + "exit";

    setUpIO(input);
    raffleApplication.run();

    String outputString = out.toString();

    assertTrue(outputString.contains(new MainMenu().toString()));
    assertTrue(outputString.contains(DRAW_NOT_ONGOING));
  }

  @Test
  @DisplayName("Starting raffle after starting draw works as expected")
  void startRaffleAfterDraw() {
    String input =
        "1"
            + System.lineSeparator()
            + "d"
            + System.lineSeparator()
            + "3"
            + System.lineSeparator()
            + "d"
            + System.lineSeparator()
            + "exit";

    setUpIO(input);
    raffleApplication.run();

    String outputString = out.toString();

    assertTrue(outputString.contains(new MainMenu().toString()));
    assertTrue(outputString.contains(new RunRaffleMenu(raffle).toString()));
  }
}
