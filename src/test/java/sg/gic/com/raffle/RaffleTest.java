package sg.gic.com.raffle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.gic.com.draw.Draw;
import sg.gic.com.mocks.MockTicketFactory;
import sg.gic.com.player.Player;
import sg.gic.com.ticket.Ticket;

public class RaffleTest {
  private Raffle raffle;
  private Draw draw;
  private MockTicketFactory factory;

  void setUpNonEmptyDraw() {
    Player john = draw.addPlayer(new Player("John"));
    Player jane = draw.addPlayer(new Player("Jane"));
    draw.buyTickets(5, john);
    draw.buyTickets(5, jane);
  }

  @BeforeEach
  void setUp() {
    factory = new MockTicketFactory();
    draw = new Draw(factory);
    raffle = new Raffle(draw, factory);
  }

  @Test
  @DisplayName("run method works as expected")
  void runGeneratesWinningTicket() {
    raffle.run();
    Ticket winningTicket = factory.generate();
    // empty run still generates winning ticket.
    assertEquals(winningTicket.toString(), raffle.getWinningTicket().toString());

    raffle.reset();
    setUpNonEmptyDraw();
    raffle.run();
    // nonempty run generates winning ticket.
    assertEquals(winningTicket.toString(), raffle.getWinningTicket().toString());
  }

  @Test
  @DisplayName("reset method resets all internal fields")
  void resetAll() {
    setUpNonEmptyDraw();

    raffle.run();
    raffle.reset();

    assertEquals(0, raffle.getTotalPayout());
    for (int i = 2; i <= 5; i++) {
      assertEquals(0, raffle.getWinners().get(i).size());
      assertEquals(0, raffle.getWinTicketsCount().get(i));
    }
    assertEquals(0.10, raffle.getWinPercentages().get(2));
    assertEquals(0.15, raffle.getWinPercentages().get(3));
    assertEquals(0.25, raffle.getWinPercentages().get(4));
    assertEquals(0.50, raffle.getWinPercentages().get(5));
  }

  @Test
  @DisplayName("run method with winning players works as expected")
  void runNonEmpty() {
    setUpNonEmptyDraw();

    raffle.run();

    assertEquals(75.0, raffle.getTotalPayout());
  }

  @Test
  @DisplayName("run method with no players works as expected")
  void runEmpty() {
    raffle.run();

    assertEquals(0, raffle.getTotalPayout());
  }
}
