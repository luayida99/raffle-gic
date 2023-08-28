package sg.gic.com.mocks;

import sg.gic.com.ticket.Ticket;
import sg.gic.com.ticket.TicketFactory;

public class MockTicketFactory extends TicketFactory {
  @Override
  public Ticket generate() {
    return new Ticket(1, 2, 3, 4, 5);
  }
}
