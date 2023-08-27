package sg.gic.com.raffle;

import sg.gic.com.player.Player;

public class Winner {
  private Player player;
  private int numWins;
  private double payout;

  public Winner(Player player, int numWins, double payout) {
    this.player = player;
    this.numWins = numWins;
    this.payout = payout;
  }

  public int getNumWins() {
    return this.numWins;
  }

  public double getPayout() {
    return this.payout;
  }

  public void setPayout(double payout) {
    this.payout = payout;
  }

  @Override
  public String toString() {
    return "%s with %d winning tickets -".formatted(player.getName(), numWins);
  }
}
