package sg.gic.com.exceptions;

/** Exception class representing case where player exceeds max allowed number of tickets. */
public class MaxTicketsExceededException extends RuntimeException {
  public MaxTicketsExceededException(String message) {
    super(message);
  }
}
