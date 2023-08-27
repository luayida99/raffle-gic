package sg.gic.com.menu.options;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collectors;

/** Class encapsulating the list of menu options presented to user in menu. */
public class MenuOptions {
  private ArrayList<MenuOption> menuOptions;

  private HashSet<Integer> optionNumbers;

  /**
   * Constructor for MenuOptions.
   *
   * @param options List of options available to user.
   */
  public MenuOptions(MenuOption... options) {
    this.menuOptions = new ArrayList<>();
    this.optionNumbers = new HashSet<>();

    for (MenuOption option : options) {
      this.menuOptions.add(option);
      this.optionNumbers.add(option.getOptionNumber());
    }
  }

  public String getOptionNumbersString() {
    StringBuilder res = new StringBuilder();

    for (int optionNumber : this.optionNumbers) {
      res.append(optionNumber);
    }

    return res.toString();
  }

  /**
   * Overridden toString method to represent list of menu options shown to user, sorted by option
   * number.
   *
   * @return Formatted string of menu options.
   */
  @Override
  public String toString() {
    this.menuOptions.sort(Comparator.comparingInt(MenuOption::getOptionNumber));
    return this.menuOptions.stream().map(MenuOption::toString).collect(Collectors.joining("\n"));
  }
}
