package sg.gic.com;

import static java.lang.Integer.parseInt;
import static sg.gic.com.utils.Constants.*;

import java.util.Arrays;
import java.util.Scanner;
import sg.gic.com.draw.Draw;
import sg.gic.com.exceptions.MaxTicketsExceededException;
import sg.gic.com.exceptions.NegativeTicketsToBuyException;
import sg.gic.com.menu.*;
import sg.gic.com.player.Player;
import sg.gic.com.raffle.Raffle;
import sg.gic.com.raffle.Runnable;
import sg.gic.com.ticket.TicketFactory;

/** Raffle application. */
public class RaffleApplication {
  private TicketFactory factory;
  private Draw draw;
  private Runnable raffle;

  public RaffleApplication(TicketFactory factory, Draw draw, Runnable raffle) {
    this.factory = factory;
    this.draw = draw;
    this.raffle = raffle;
  }

  /**
   * Helper method encapsulating run of Start Draw option.
   *
   * @param sc Scanner for IO.
   * @param mainMenu Main menu to be shown.
   */
  private void handleStartDraw(Scanner sc, MainMenu mainMenu) {
    // End draw, then start a new one
    draw.end();
    draw.isOngoing();
    DrawStartedMenu drawStartedMenu = new DrawStartedMenu(draw.getDrawPool());
    drawStartedMenu.display();
    // Write to System.in to continue
    sc.next();
    mainMenu.updateOngoingStatus(draw.getDrawPool());
    mainMenu.display();
  }

  /**
   * Helper method encapsulating run of Buy Tickets option.
   *
   * @param sc Scanner for IO
   * @param mainMenu Main menu to be shown.
   * @param buyTicketsPromptMenu Buy tickets prompt to be shown.
   */
  private void handleBuyTickets(
      Scanner sc, MainMenu mainMenu, BuyTicketsPromptMenu buyTicketsPromptMenu) {
    // Cannot buy if draw not started.
    if (!draw.getOngoing()) {
      System.out.println(DRAW_NOT_ONGOING + "\n");
      mainMenu.display();
      return;
    }

    buyTicketsPromptMenu.display();
    String buyTicketLine = sc.nextLine();
    String[] splitBuyTicketLine = buyTicketLine.split(",");
    String[] trimmedLine =
        Arrays.stream(splitBuyTicketLine).map(String::trim).toArray(String[]::new);

    if (trimmedLine.length != 2) {
      System.out.println(BUY_TICKET_INPUT_WRONG + "\n");
      mainMenu.display();
      return;
    }

    try {
      String name = trimmedLine[0];
      int numTicketsToBuy = parseInt(trimmedLine[1]);
      Player player = draw.addPlayer(new Player(name));
      draw.buyTickets(numTicketsToBuy, player);
      mainMenu.updateOngoingStatus(draw.getDrawPool());

      PurchasedTicketsMenu purchasedTicketsMenu = new PurchasedTicketsMenu(player, numTicketsToBuy);
      purchasedTicketsMenu.display();
    } catch (NumberFormatException e) {
      System.out.println(NON_INTEGER_TICKETS_PURCHASE + "\n");
    } catch (MaxTicketsExceededException | NegativeTicketsToBuyException e) {
      System.out.println(e.getMessage());
    }
    mainMenu.display();
  }

  /**
   * Helper method encapsulating run of Run Raffle option.
   *
   * @param sc Scanner for IO.
   * @param mainMenu Main menu to be shown.
   * @param runRaffleMenu Raffle run menu to be shown.
   */
  private void handleRunRaffle(Scanner sc, MainMenu mainMenu, RunRaffleMenu runRaffleMenu) {
    // Cannot run raffle if draw not started.
    if (!draw.getOngoing()) {
      System.out.println(DRAW_NOT_ONGOING + "\n");
      mainMenu.display();
      return;
    }
    raffle.run();
    runRaffleMenu.display();
    raffle.reset();
    draw.end();
    mainMenu.resetStatus();
    // Write to System.in to continue
    sc.next();
    mainMenu.display();
  }

  /** Main logic for application. */
  public void run() {
    MainMenu mainMenu = new MainMenu();
    BuyTicketsPromptMenu buyTicketsPromptMenu = new BuyTicketsPromptMenu();
    // Safe cast, main method instantiates as Raffle classtype.
    RunRaffleMenu runRaffleMenu = new RunRaffleMenu((Raffle) raffle);

    mainMenu.display();
    Scanner sc = new Scanner(System.in);
    String input = "";

    while (!input.equals("exit")) {
      input = sc.nextLine();
      input.trim();

      if (input.equals("exit")) {
        System.out.println(EXIT_APP + "\n");
        continue;
      }

      if (!mainMenu.getOptionNumbersString().contains(input)) {
        System.out.println(WRONG_INPUT + "\n");
        mainMenu.display();
      }

      switch (input) {
        case "1" -> handleStartDraw(sc, mainMenu);
        case "2" -> handleBuyTickets(sc, mainMenu, buyTicketsPromptMenu);
        case "3" -> handleRunRaffle(sc, mainMenu, runRaffleMenu);
      }
    }
  }

  /**
   * Application entry point.
   *
   * @param args CLI arguments, unused.
   */
  public static void main(String[] args) {
    TicketFactory factory = new TicketFactory();
    Draw draw = new Draw(factory);
    Raffle raffle = new Raffle(draw, factory);

    RaffleApplication application = new RaffleApplication(factory, draw, raffle);
    application.run();
  }
}
