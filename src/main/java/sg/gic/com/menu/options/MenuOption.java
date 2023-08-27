package sg.gic.com.menu.options;

/** Class encapsulating a menu option that is available in the menu. */
public class MenuOption {
  private int optionNumber;
  private String optionDescription;

  public MenuOption(int optionNumber, String optionDescription) {
    this.optionNumber = optionNumber;
    this.optionDescription = optionDescription;
  }

  public int getOptionNumber() {
    return this.optionNumber;
  }

  /**
   * Overridden toString method to represent menu option displayed to user.
   *
   * @return Formatted string of option.
   */
  @Override
  public String toString() {
    return "[%s] %s".formatted(this.optionNumber, this.optionDescription);
  }
}
