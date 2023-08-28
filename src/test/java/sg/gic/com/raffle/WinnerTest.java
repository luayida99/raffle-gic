package sg.gic.com.raffle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.gic.com.player.Player;

public class WinnerTest {
  private Winner winner;

  @BeforeEach
  void setUp() {
    winner = new Winner(new Player("John"), 1, 10);
  }

  @Test
  @DisplayName("toString works as expected")
  void toStringTest() {
    String winnerString = "John with 1 winning ticket(s) -";

    assertEquals(winnerString, winner.toString());
  }
}
