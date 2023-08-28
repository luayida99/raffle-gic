package sg.gic.com.player;

import static sg.gic.com.utils.Constants.*;

import java.util.ArrayList;
import java.util.StringJoiner;
import sg.gic.com.ticket.Ticket;

/** Class encapsulating a player in the raffle. */
public class Player {
  private String name;
  private ArrayList<Ticket> tickets;
  private int numTickets;

  public Player(String name) {
    this.name = name;
    this.tickets = new ArrayList<>();
    this.numTickets = 0;
  }

  public String getName() {
    return this.name;
  }

  public ArrayList<Ticket> getTickets() {
    return this.tickets;
  }

  public int getNumTickets() {
    return this.numTickets;
  }

  /** Increments number of tickets owned by player. */
  public void incrementNumTickets() {
    this.numTickets++;
  }

  /**
   * Adds ticket to player.
   *
   * @param ticket Ticket generated for player.
   */
  public void addTicket(Ticket ticket) {
    this.tickets.add(ticket);
  }

  /**
   * Checks if 2 players are equal by name.
   *
   * @param player Player to check against.
   * @return Boolean indicating if 2 players are equal.
   */
  public boolean matches(Player player) {
    return this.name.equals(player.name);
  }

  /**
   * String representation of tickets owned by player.
   *
   * @return String representation of tickets.
   */
  public String ticketsToString() {
    StringJoiner joiner = new StringJoiner("\n");

    // start at index 1
    for (int i = 1; i < this.numTickets + 1; i++) {
      String ticketString = this.tickets.get(i - 1).toString();
      joiner.add("Ticket %d: %s".formatted(i, ticketString));
    }

    return joiner.toString();
  }
}
