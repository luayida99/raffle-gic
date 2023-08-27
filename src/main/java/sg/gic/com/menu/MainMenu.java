package sg.gic.com.menu;

import static sg.gic.com.utils.Constants.BUY_TICKETS_OPTION;
import static sg.gic.com.utils.Constants.DEFAULT_POOL;
import static sg.gic.com.utils.Constants.DRAW_NOT_STARTED_STATUS;
import static sg.gic.com.utils.Constants.DRAW_ONGOING_STATUS;
import static sg.gic.com.utils.Constants.RUN_RAFFLE_OPTION;
import static sg.gic.com.utils.Constants.START_NEW_DRAW_OPTION;
import static sg.gic.com.utils.Constants.WELCOME_MESSAGE;

import sg.gic.com.menu.options.MenuOption;
import sg.gic.com.menu.options.MenuOptions;

/** Class encapsulating the main menu shown to players. */
public class MainMenu extends Menu {
  private String status;
  private MenuOptions options;
  private double drawPool;

  public MainMenu() {
    MenuOption option1 = new MenuOption(1, START_NEW_DRAW_OPTION);
    MenuOption option2 = new MenuOption(2, BUY_TICKETS_OPTION);
    MenuOption option3 = new MenuOption(3, RUN_RAFFLE_OPTION);

    this.status = DRAW_NOT_STARTED_STATUS;
    this.options = new MenuOptions(option1, option2, option3);
    this.drawPool = DEFAULT_POOL;
  }

  /**
   * Updates status to reflect ongoing raffle.
   *
   * @param updatedPool Current amount of raffle pool.
   */
  public void updateOngoingStatus(double updatedPool) {
    this.drawPool = updatedPool;
    String ongoingStatus = "%s $%.2f".formatted(DRAW_ONGOING_STATUS, this.drawPool);
    this.status = ongoingStatus;
  }

  /** Resets status at the end of raffle. */
  public void resetStatus() {
    this.status = DRAW_NOT_STARTED_STATUS;
  }

  /**
   * Overridden toString method representing main menu to be shown.
   *
   * @return String representing main menu to be shown.
   */
  @Override
  public String toString() {
    String welcomeLine = WELCOME_MESSAGE;
    String statusLine = "Status: %s".formatted(this.status);
    return "%s \n%s \n\n%s".formatted(welcomeLine, statusLine, options.toString());
  }

  public String getOptionNumbersString() {
    return this.options.getOptionNumbersString();
  }
}
