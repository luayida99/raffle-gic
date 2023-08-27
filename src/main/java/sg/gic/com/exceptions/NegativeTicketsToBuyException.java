package sg.gic.com.exceptions;

public class NegativeTicketsToBuyException extends RuntimeException {
  public NegativeTicketsToBuyException(String message) {
    super(message);
  }
}
