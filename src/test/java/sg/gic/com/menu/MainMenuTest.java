package sg.gic.com.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MainMenuTest {
  private MainMenu mainMenu;

  @BeforeEach
  void setUp() {
    mainMenu = new MainMenu();
  }

  @Test
  @DisplayName("New MainMenu toString works as expected")
  void toStringDefaultMainMenu() {
    String menuString =
        "Welcome to My Raffle App \n"
            + "Status: Draw has not started \n"
            + "\n"
            + "[1] Start a New Draw\n"
            + "[2] Buy Tickets\n"
            + "[3] Run Raffle";
    assertEquals(menuString, mainMenu.toString());
  }

  @Test
  @DisplayName("MainMenu with updated status toString works as expected")
  void toStringUpdatedMainMenu() {
    String updatedMenuString =
        "Welcome to My Raffle App \n"
            + "Status: Draw is ongoing. Raffle pot size is $105.00 \n"
            + "\n"
            + "[1] Start a New Draw\n"
            + "[2] Buy Tickets\n"
            + "[3] Run Raffle";
    mainMenu.updateOngoingStatus(105);
    assertEquals(updatedMenuString, mainMenu.toString());
  }

  @Test
  @DisplayName("MainMenu with reset status toString works as expected")
  void toStringResetMainMenu() {
    String menuString =
        "Welcome to My Raffle App \n"
            + "Status: Draw has not started \n"
            + "\n"
            + "[1] Start a New Draw\n"
            + "[2] Buy Tickets\n"
            + "[3] Run Raffle";
    mainMenu.updateOngoingStatus(105);
    mainMenu.resetStatus();
    assertEquals(menuString, mainMenu.toString());
  }
}
