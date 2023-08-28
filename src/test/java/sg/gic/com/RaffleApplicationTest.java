package sg.gic.com;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.gic.com.draw.Draw;
import sg.gic.com.mocks.MockTicketFactory;
import sg.gic.com.raffle.Raffle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RaffleApplicationTest {
  private RaffleApplication raffleApplication;
  private MockTicketFactory factory;
  private Draw draw;

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
    raffleApplication = new RaffleApplication(factory, draw, new Raffle(draw, factory));
  }

    @Test
    @DisplayName("Starting a draw works as expected")
    void startDrawTest() {
      String input = "1" + System.lineSeparator() + "d" + System.lineSeparator() + "exit";
      String expectedOutput = "Welcome to My Raffle App \n" +
              "Status: Draw has not started \n" +
              "\n" +
              "[1] Start a New Draw\n" +
              "[2] Buy Tickets\n" +
              "[3] Run Raffle\n" +
              "New Raffle draw has been started. Initial pot size: $100.00 \n" +
              "Press any key to return to main menu\n" +
              "Welcome to My Raffle App \n" +
              "Status: Draw is ongoing. Raffle pot size is $100.00 \n" +
              "\n" +
              "[1] Start a New Draw\n" +
              "[2] Buy Tickets\n" +
              "[3] Run Raffle\n" +
              "Wrong input, please try again.\n" +
              "\n" +
              "Welcome to My Raffle App \n" +
              "Status: Draw is ongoing. Raffle pot size is $100.00 \n" +
              "\n" +
              "[1] Start a New Draw\n" +
              "[2] Buy Tickets\n" +
              "[3] Run Raffle\n";

      setUpIO(input);
      raffleApplication.run();

      assertEquals(expectedOutput, out.toString());
    }
}
