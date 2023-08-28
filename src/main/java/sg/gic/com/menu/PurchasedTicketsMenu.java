package sg.gic.com.menu;

import sg.gic.com.player.Player;

/** Class encapsulating menu shown to player after buying tickets. */
public class PurchasedTicketsMenu extends Menu {
  private Player player;
  private int numTicketsPurchased;

  public PurchasedTicketsMenu(Player player, int numTicketsPurchased) {
    this.player = player;
    this.numTicketsPurchased = numTicketsPurchased;
  }

  /**
   * Overridden toString method representing menu to be shown to player after buying ticket.
   *
   * @return String representing menu to be shown to player.
   */
  @Override
  public String toString() {
    return "Hi %s, you have purchased %d ticket(s) - \n%s"
        .formatted(player.getName(), this.numTicketsPurchased, player.ticketsToString());
  }
}
