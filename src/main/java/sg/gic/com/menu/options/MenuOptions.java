package sg.gic.com.menu.options;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collectors;

/** Class encapsulating the list of menu options presented to user in menu. */
public class MenuOptions {
  private ArrayList<MenuOption> menuOptions;

  private HashSet<Integer> optionNumbers;

  public MenuOptions(MenuOption... options) {
    this.menuOptions = new ArrayList<>();
    this.optionNumbers = new HashSet<>();

    for (MenuOption option : options) {
      this.menuOptions.add(option);
      this.optionNumbers.add(option.getOptionNumber());
    }
  }

  /**
   * Helper method to return string concatenation of all option numbers.
   *
   * @return Array of all option numbers.
   */
  public ArrayList<String> getOptionNumbersArray() {
    ArrayList<String> res = new ArrayList<>();

    for (int optionNumber : this.optionNumbers) {
      res.add(Integer.toString(optionNumber));
    }

    return res;
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
