package sg.gic.com.menu;

/** Abstract class representing shared functionality between menus. */
public abstract class Menu {
  /** Displays menu. */
  public void display() {
    System.out.println(this.toString());
  }
}
