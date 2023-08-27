package sg.gic.com.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.gic.com.utils.Constants.START_NEW_DRAW_OPTION;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.gic.com.menu.options.MenuOption;

public class MenuOptionTest {
  private MenuOption option;

  @BeforeEach
  void setUp() {
    option = new MenuOption(1, START_NEW_DRAW_OPTION);
  }

  @Test
  @DisplayName("getOptionNumber works as expected")
  void getOptionNumberTest() {
    assertEquals(1, option.getOptionNumber());
  }

  @Test
  @DisplayName("toString formats string correctly")
  void toStringTest() {
    String correctString = "[1] Start a New Draw";
    assertEquals(correctString, option.toString());
  }
}
