package sg.gic.com;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.BeforeEach;
import sg.gic.com.draw.Draw;
import sg.gic.com.raffle.Raffle;
import sg.gic.com.ticket.TicketFactory;

public class RaffleApplicationTest {
  private RaffleApplication raffleApplication;
  private InputStream in;
  private ByteArrayOutputStream out;

  private void setUpIO(String input) {
    InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    System.setIn(in);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
  }

  @BeforeEach
  void setUp() {
    TicketFactory factory = new TicketFactory();
    Draw draw = new Draw(factory);
    raffleApplication = new RaffleApplication(factory, draw, new Raffle(draw, factory));
  }

  //  @Test
  //  @DisplayName("Option 1 works as expected")
  //  void startDrawTest() {
  //    String input = "1\nd\n";
  //    setUpIO(input);
  //
  //    raffleApplication.run();
  //    assertEquals("", out.toString());
  //  }
}
