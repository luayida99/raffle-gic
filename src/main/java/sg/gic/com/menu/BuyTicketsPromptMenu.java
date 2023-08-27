package sg.gic.com.menu;

import static sg.gic.com.utils.Constants.BUY_TICKETS_PROMPT;

/** Class encapsulating menu shown to prompt player to buy tickets. */
public class BuyTicketsPromptMenu extends Menu {
  /**
   * Overridden toString method representing menu to be shown.
   *
   * @return String representing menu to be shown.
   */
  @Override
  public String toString() {
    return BUY_TICKETS_PROMPT;
  }
}
