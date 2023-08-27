package sg.gic.com.menu;

import static sg.gic.com.utils.Constants.NEW_RAFFLE_STARTED_MESSAGE;
import static sg.gic.com.utils.Constants.RETURN_TO_MAIN_MENU_MESSAGE;

/** Class encapsulating the menu shown to players when starting a new raffle. */
public class DrawStartedMenu extends Menu {
  private double drawPool;

  /** Constructor for RaffleStartedMenu. */
  public DrawStartedMenu(double drawPool) {
    this.drawPool = drawPool;
  }

  /**
   * Overridden toString method representing menu shown when starting a new raffle.
   *
   * @return String representing menu shown to players when starting a new raffle.
   */
  @Override
  public String toString() {
    return "%s $%.2f \n%s"
        .formatted(NEW_RAFFLE_STARTED_MESSAGE, this.drawPool, RETURN_TO_MAIN_MENU_MESSAGE);
  }
}
