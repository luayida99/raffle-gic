package sg.gic.com.raffle;

import sg.gic.com.player.Player;

public record Winner(Player player, int numWins) {
  @Override
  public String toString() {
    return "%s with %d winning tickets -".formatted(player.getName(), numWins);
  }
}
