package sg.gic.com.exceptions;

public class MaxTicketsExceededException extends RuntimeException {
  public MaxTicketsExceededException(String message) {
    super(message);
  }
}
