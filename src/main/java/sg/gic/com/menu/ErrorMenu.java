package sg.gic.com.menu;

/** Class encapsulating error menu shown upon encountering error. */
public class ErrorMenu extends Menu {
  private String errorString;

  public ErrorMenu(String errorString) {
    this.errorString = errorString;
  }

  /**
   * Updates error to be shown.
   *
   * @param newErrorString New error to be shown.
   */
  public void updateError(String newErrorString) {
    this.errorString = newErrorString;
  }

  /**
   * Overridden toString method representing menu to be shown.
   *
   * @return String representing menu to be shown.
   */
  @Override
  public String toString() {
    return this.errorString;
  }
}
