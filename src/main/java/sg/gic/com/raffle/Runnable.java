package sg.gic.com.raffle;

/** Interface abstracting computation at end of raffle. */
public interface Runnable {
  /** Compute at end of raffle. */
  void run();

  /** Reset at end of raffle. */
  void reset();
}
