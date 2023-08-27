package sg.gic.com.menu;

import sg.gic.com.raffle.Raffle;

/** Class encapsulating menu shown to players when running the raffle. */
public class RunRaffleMenu extends Menu {
  Raffle raffle;

  public RunRaffleMenu(Raffle raffle) {
    this.raffle = raffle;
  }

  /**
   * Overridden toString method representing menu shown to player after raffle run.
   *
   * @return String representing menu shown to player after raffle run.
   */
  @Override
  public String toString() {
    return this.raffle.toString();
  }
}
