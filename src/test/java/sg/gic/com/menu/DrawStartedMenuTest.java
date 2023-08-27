package sg.gic.com.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.gic.com.utils.Constants.DEFAULT_POOL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DrawStartedMenuTest {
  private DrawStartedMenu menu;

  @BeforeEach
  void setUp() {
    menu = new DrawStartedMenu(DEFAULT_POOL);
  }

  @Test
  @DisplayName("toString works as expected")
  void toStringRaffleStartedMenu() {
    String correctString =
        "New Raffle draw has been started. Initial pot size: $100.00 \n"
            + "Press any key to return to main menu";
    assertEquals(correctString, menu.toString());
  }
}
