package sg.gic.com.menu;

import sg.gic.com.raffle.Raffle;

public class RunRaffleMenu extends Menu {
  Raffle raffle;

  public RunRaffleMenu(Raffle raffle) {
    this.raffle = raffle;
  }

  @Override
  public String toString() {
    return this.raffle.toString();
  }
}
