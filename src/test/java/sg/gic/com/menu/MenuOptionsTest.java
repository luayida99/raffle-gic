package sg.gic.com.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.gic.com.utils.Constants.BUY_TICKETS_OPTION;
import static sg.gic.com.utils.Constants.RUN_RAFFLE_OPTION;
import static sg.gic.com.utils.Constants.START_NEW_DRAW_OPTION;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.gic.com.menu.options.MenuOption;
import sg.gic.com.menu.options.MenuOptions;

public class MenuOptionsTest {
  private MenuOptions correctlyOrderedOptions;
  private MenuOptions wronglyOrderedOptions;

  private MenuOption option1;
  private MenuOption option2;
  private MenuOption option3;

  private String orderedOptionsString;

  @BeforeEach
  void setUp() {
    MenuOptionsTest menuOptionsTest = new MenuOptionsTest();

    option1 = new MenuOption(1, START_NEW_DRAW_OPTION);
    option2 = new MenuOption(2, BUY_TICKETS_OPTION);
    option3 = new MenuOption(3, RUN_RAFFLE_OPTION);

    correctlyOrderedOptions = new MenuOptions(option1, option2, option3);
    wronglyOrderedOptions = new MenuOptions(option3, option2, option1);

    orderedOptionsString = "[1] Start a New Draw\n" + "[2] Buy Tickets\n" + "[3] Run Raffle";
  }

  @Test
  @DisplayName("toString of correctly ordered MenuOptions works as expected")
  void toStringCorrectlyOrdered() {
    assertEquals(orderedOptionsString, correctlyOrderedOptions.toString());
  }

  @Test
  @DisplayName("toString of wrongly ordered MenuOptions sorts and works as expected")
  void toStringWronglyOrdered() {
    assertEquals(orderedOptionsString, wronglyOrderedOptions.toString());
  }
}
