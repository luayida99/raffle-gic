package sg.gic.com.ticket;

import static sg.gic.com.utils.Constants.TICKET_COST;

import java.util.TreeSet;
import java.util.stream.Collectors;

/** Class encapsulating a ticket that can be bought by a user. */
public class Ticket {
  private int cost;
  // Use TreeSet to maintain total order for printing.
  private TreeSet<Integer> ticketNumbers;

  /**
   * Constructor for Ticket.
   *
   * @param numbers List of numbers on the ticket.
   */
  public Ticket(Integer... numbers) {
    this.cost = TICKET_COST;
    this.ticketNumbers = new TreeSet<>();
    for (int number : numbers) {
      this.ticketNumbers.add(number);
    }
  }

  /**
   * Getter for ticketNumbers.
   *
   * @return ticketNumbers.
   */
  public TreeSet<Integer> getTicketNumbers() {
    return this.ticketNumbers;
  }

  /**
   * Getter for cost.
   *
   * @return Ticket cost.
   */
  public int getCost() {
    return this.cost;
  }

  /**
   * Returns number of common numbers with a given Ticket.
   *
   * @param ticket Ticket to match against.
   * @return Number of common numbers with ticket.
   */
  public int matches(Ticket ticket) {
    TreeSet<Integer> matches = new TreeSet<>();
    matches.addAll(this.ticketNumbers);
    matches.retainAll(ticket.getTicketNumbers());

    return matches.size();
  }

  /**
   * Overridden toString method representing numbers present in ticket in ascending order.
   *
   * @return Formatted string of numbers in ticket.
   */
  @Override
  public String toString() {
    return this.ticketNumbers.stream()
        .map(number -> number.toString())
        .collect(Collectors.joining(" "));
  }
}
