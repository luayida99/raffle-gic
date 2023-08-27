package sg.gic.com.draw;

import static sg.gic.com.utils.Constants.*;
import static sg.gic.com.utils.Constants.MAX_TICKETS_EXCEEDED_EXCEPTION;

import java.util.ArrayList;
import sg.gic.com.exceptions.MaxTicketsExceededException;
import sg.gic.com.exceptions.NegativeTicketsToBuyException;
import sg.gic.com.player.Player;
import sg.gic.com.ticket.Ticket;
import sg.gic.com.ticket.TicketFactory;

/** Class encapsulating a raffle draw. */
public class Draw {
  private boolean ongoing;
  private double drawPool;
  private ArrayList<Player> players;
  private static TicketFactory factory;

  public Draw(TicketFactory factory) {
    this.ongoing = false;
    this.drawPool = DEFAULT_POOL;
    this.players = new ArrayList<>();
    Draw.factory = factory;
  }

  public double getDrawPool() {
    return this.drawPool;
  }

  public ArrayList<Player> getPlayers() {
    return this.players;
  }

  /**
   * Checks if name is in draw, and adds a new player or retrieves existing player.
   *
   * @param name Name to check for.
   * @return Player that is added or retrieved.
   */
  public Player addPlayer(String name) {
    if (!this.containsPlayer(name)) {
      Player player = new Player(name);
      this.players.add(player);
      return player;
    } else {
      // If containsPlayer, player is unique
      return this.players.stream()
          .filter(existingPlayer -> existingPlayer.matches(name))
          .findFirst()
          .get();
    }
  }

  /**
   * Checks if draw contains player with a given name.
   *
   * @param name Name to check for.
   * @return Boolean indicating if player with given name exists.
   */
  private boolean containsPlayer(String name) {
    boolean containsPlayer = false;

    for (Player existingPlayer : this.players) {
      if (existingPlayer.matches(name)) {
        containsPlayer = true;
        break;
      }
    }

    return containsPlayer;
  }

  /**
   * Adds to raffle pool.
   *
   * @param amount Amount to add to raffle pool.
   */
  private void addToDrawPool(double amount) {
    this.drawPool += amount;
  }

  /**
   * Pays out winnings to player who won.
   *
   * @param amount Amount to pay out.
   */
  public void payout(double amount) {
    this.drawPool -= amount;
  }

  /**
   * Buys tickets for a given player.
   *
   * @param numToBuy Number of tickets to buy for given player.
   * @param player Player to buy tickets for.
   * @throws MaxTicketsExceededException Players will exceed max allowed tickets.
   * @throws NegativeTicketsToBuyException Player tried to buy negative tickets.
   */
  public void buyTickets(int numToBuy, Player player)
      throws MaxTicketsExceededException, NegativeTicketsToBuyException {
    if (numToBuy < 0) {
      throw new NegativeTicketsToBuyException(NEGATIVE_TICKETS_EXCEPTION + "\n");
    }
    if (player.getNumTickets() + numToBuy > MAX_TICKETS) {
      throw new MaxTicketsExceededException(
          MAX_TICKETS_EXCEEDED_EXCEPTION + player.getNumTickets() + "\n");
    }

    for (int i = 0; i < numToBuy; i++) {
      Ticket generatedTicket = factory.generate();
      player.addTicket(generatedTicket);
      player.incrementNumTickets();
      this.addToDrawPool(generatedTicket.getCost());
    }
  }

  public boolean getOngoing() {
    return this.ongoing;
  }

  /** Indication that draw is ongoing. */
  public void isOngoing() {
    this.ongoing = true;
  }

  /** Ends draw and resets players. */
  public void end() {
    this.ongoing = false;
    this.players.removeAll(this.players);
  }
}
