package sg.gic.com.draw;

import static sg.gic.com.utils.Constants.*;
import static sg.gic.com.utils.Constants.MAX_TICKETS_EXCEEDED_EXCEPTION;

import java.util.ArrayList;
import sg.gic.com.exceptions.MaxTicketsExceededException;
import sg.gic.com.exceptions.NegativeTicketsToBuyException;
import sg.gic.com.player.Player;
import sg.gic.com.ticket.Ticket;
import sg.gic.com.ticket.TicketFactory;

/** Class encapsulating the raffle draw. */
public class Draw {
  private boolean ongoing;
  private int drawPool;
  private ArrayList<Player> players;
  private static TicketFactory factory;

  /** Constructor for Draw. */
  public Draw(TicketFactory factory) {
    this.ongoing = false;
    this.drawPool = DEFAULT_POOL;
    this.players = new ArrayList<>();
    Draw.factory = factory;
  }

  /**
   * Getter for raffle pool.
   *
   * @return Current raffle pool.
   */
  public int getDrawPool() {
    return this.drawPool;
  }

  /**
   * Getter for players in draw.
   *
   * @return Players.
   */
  public ArrayList<Player> getPlayers() {
    return this.players;
  }

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
  private void addToDrawPool(int amount) {
    this.drawPool += amount;
  }

  public void buyTickets(int numToBuy, Player player)
      throws MaxTicketsExceededException, NegativeTicketsToBuyException {
    if (numToBuy < 0) {
      throw new NegativeTicketsToBuyException(NEGATIVE_TICKETS_EXCEPTION);
    }
    if (player.getNumTickets() + numToBuy > MAX_TICKETS) {
      throw new MaxTicketsExceededException(
          MAX_TICKETS_EXCEEDED_EXCEPTION + player.getNumTickets());
    }

    for (int i = 0; i < numToBuy; i++) {
      Ticket generatedTicket = factory.generate();
      player.addTicket(generatedTicket);
      player.incrementNumTickets();
      this.addToDrawPool(generatedTicket.getCost());
    }
    System.out.println(player.ticketsToString());
  }

  public boolean getOngoing() {
    return this.ongoing;
  }

  public void isOngoing() {
    this.ongoing = true;
  }

  public void end() {
    this.ongoing = false;
    this.players = new ArrayList<>();
  }
}
