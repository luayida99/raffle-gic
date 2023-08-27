package sg.gic.com.exceptions;

/** Exception class representing case where player tries to buy negative number of tickets. */
public class NegativeTicketsToBuyException extends RuntimeException {
  public NegativeTicketsToBuyException(String message) {
    super(message);
  }
}
