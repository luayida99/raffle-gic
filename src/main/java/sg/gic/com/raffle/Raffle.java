package sg.gic.com.raffle;

import static sg.gic.com.utils.Constants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;
import sg.gic.com.draw.Draw;
import sg.gic.com.player.Player;
import sg.gic.com.ticket.Ticket;
import sg.gic.com.ticket.TicketFactory;

/** Class encapsulating a raffle run that computes winners. */
public class Raffle implements Runnable {

  private Draw draw;
  private Ticket winningTicket;
  private double totalPayout;
  private static TicketFactory factory;

  private HashMap<Integer, ArrayList<Winner>> winners;
  private HashMap<Integer, Double> winPercentages;
  private HashMap<Integer, Integer> winTicketsCount;

  public Raffle(Draw draw, TicketFactory factory) {
    Raffle.factory = factory;
    this.draw = draw;
    reset();
  }

  public Ticket getWinningTicket() {
    return this.winningTicket;
  }

  public double getTotalPayout() {
    return this.totalPayout;
  }

  public HashMap<Integer, ArrayList<Winner>> getWinners() {
    return this.winners;
  }

  public HashMap<Integer, Integer> getWinTicketsCount() {
    return this.winTicketsCount;
  }

  public HashMap<Integer, Double> getWinPercentages() {
    return this.winPercentages;
  }

  /** Resets raffle. */
  public void reset() {
    this.totalPayout = 0;
    // Initialise winners of different categories.
    this.winners = new HashMap<>();
    this.winners.put(2, new ArrayList<>());
    this.winners.put(3, new ArrayList<>());
    this.winners.put(4, new ArrayList<>());
    this.winners.put(5, new ArrayList<>());
    // Add win percentages by category.
    this.winPercentages = new HashMap<>();
    this.winPercentages.put(2, 0.10);
    this.winPercentages.put(3, 0.15);
    this.winPercentages.put(4, 0.25);
    this.winPercentages.put(5, 0.50);
    // Initialise no of winning tickets by category.
    this.winTicketsCount = new HashMap<>();
    this.winTicketsCount.put(2, 0);
    this.winTicketsCount.put(3, 0);
    this.winTicketsCount.put(4, 0);
    this.winTicketsCount.put(5, 0);
  }

  /**
   * Computes the number of wins in each category for given player.
   *
   * @param player Player to compute number of wins for.
   */
  private void computeWinsByPlayer(Player player) {
    // Track no of winning tickets per category.
    HashMap<Integer, Integer> numWins = new HashMap<>();
    numWins.put(2, 0);
    numWins.put(3, 0);
    numWins.put(4, 0);
    numWins.put(5, 0);

    for (Ticket ticket : player.getTickets()) {
      int numMatches = ticket.matches(this.winningTicket);
      if (numMatches <= 1) {
        continue;
      }
      int currWins = numWins.get(numMatches) + 1;
      numWins.put(numMatches, currWins);
    }

    for (int category : numWins.keySet()) {
      int numWinningTickets = numWins.get(category);
      int winCount = this.winTicketsCount.get(category) + numWinningTickets;
      Winner winner = new Winner(player, numWinningTickets, 0);

      this.winTicketsCount.put(category, winCount);
      this.winners.get(category).add(winner);
    }
  }

  /**
   * Computes payout for winner in a category, and updates total payout of raffle.
   *
   * @param category Category to check.
   * @param winner Winner to check.
   * @return Payout for a winner in the given category.
   */
  private double computeWinnerPayoutByCategory(int category, Winner winner) {
    double categoryWinAmount = this.draw.getDrawPool() * this.winPercentages.get(category);
    double winAmountPerTicket = categoryWinAmount / this.winTicketsCount.get(category);
    double winAmount = winAmountPerTicket * winner.getNumWins();
    winner.setPayout(winAmount);

    return winAmount;
  }

  /** Runs raffle. */
  public void run() {
    this.winningTicket = factory.generate();

    for (Player player : this.draw.getPlayers()) {
      computeWinsByPlayer(player);
    }

    for (int category : this.winners.keySet()) {
      if (this.winTicketsCount.get(category) == 0) {
        continue;
      }

      for (Winner winner : this.winners.get(category)) {
        this.totalPayout += computeWinnerPayoutByCategory(category, winner);
      }
    }
    this.draw.payout(this.totalPayout);
  }

  /**
   * Overridden toString method representing raffle results.
   *
   * @return String representing raffle results.
   */
  @Override
  public String toString() {
    StringJoiner joiner = new StringJoiner("\n");

    joiner.add(RUN_RAFFLE_MESSAGE);
    String winningTicketLine = "Winning Ticket is %s".formatted(this.winningTicket.toString());
    joiner.add(winningTicketLine);

    for (int category : this.winners.keySet()) {
      StringBuilder builder = new StringBuilder();
      String categoryHeaderLine =
          category == JACKPOT_CATEGORY
              ? "\nGroup %d Winners (Jackpot): \n".formatted(category)
              : "\nGroup %d Winners: \n".formatted(category);
      builder.append(categoryHeaderLine);

      if (this.winTicketsCount.get(category) == 0) {
        builder.append("Nil\n");
        String nilLine = builder.toString();
        joiner.add(nilLine);
        continue;
      }

      for (Winner winner : this.winners.get(category)) {
        if (winner.getNumWins() == 0) {
          continue;
        }
        String winnerLine = "%s $%.2f\n".formatted(winner.toString(), winner.getPayout());
        builder.append(winnerLine);
      }
      builder.append("\n");
      String categoryLines = builder.toString();
      joiner.add(categoryLines);
    }

    joiner.add(RETURN_TO_MAIN_MENU_MESSAGE);
    return joiner.toString();
  }
}
