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

  /**
   * Constructor for Player.
   *
   * @param name Name of player.
   */
  public Player(String name) {
    this.name = name;
    this.tickets = new ArrayList<>();
    this.numTickets = 0;
  }

  /**
   * Getter for name.
   *
   * @return Player name.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Getters for tickets.
   *
   * @return Tickets owned by player.
   */
  public ArrayList<Ticket> getTickets() {
    return this.tickets;
  }

  /**
   * Getter for numTickets.
   *
   * @return Number of tickets owned by player.
   */
  public int getNumTickets() {
    return this.numTickets;
  }

  public void incrementNumTickets() {
    this.numTickets++;
  }

  public void addTicket(Ticket ticket) {
    this.tickets.add(ticket);
  }

  /**
   * Checks if 2 players are equal by name.
   *
   * @param name Player name to check against.
   * @return Boolean indicating if 2 players are equal.
   */
  public boolean matches(String name) {
    return this.name.equals(name);
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
