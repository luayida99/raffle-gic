package sg.gic.com;

import static java.lang.Integer.parseInt;
import static sg.gic.com.utils.Constants.BUY_TICKET_INPUT_WRONG;
import static sg.gic.com.utils.Constants.DRAW_NOT_ONGOING;

import java.util.Arrays;
import java.util.Scanner;
import sg.gic.com.draw.Draw;
import sg.gic.com.exceptions.MaxTicketsExceededException;
import sg.gic.com.exceptions.NegativeTicketsToBuyException;
import sg.gic.com.menu.*;
import sg.gic.com.player.Player;
import sg.gic.com.raffle.Raffle;
import sg.gic.com.ticket.TicketFactory;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class RaffleApplication {
  public RaffleApplication() {}

  private void run() {
    TicketFactory factory = new TicketFactory();
    Draw draw = new Draw(factory);
    Raffle raffle = new Raffle(draw, factory);

    MainMenu mainMenu = new MainMenu();
    BuyTicketsPromptMenu buyTicketsPromptMenu = new BuyTicketsPromptMenu();
    RunRaffleMenu runRaffleMenu = new RunRaffleMenu(raffle);

    mainMenu.display();
    Scanner sc = new Scanner(System.in);
    String input;

    while (true) {
      input = sc.nextLine();
      input.trim();

      switch (input) {
        case "1":
          // End draw, then start a new one
          draw.end();
          draw.isOngoing();
          DrawStartedMenu drawStartedMenu = new DrawStartedMenu(draw.getDrawPool());
          drawStartedMenu.display();
          // Write to System.in to continue
          sc.next();
          mainMenu.updateOngoingStatus(draw.getDrawPool());
          mainMenu.display();
          break;
        case "2":
          // Cannot buy if draw not started.
          if (!draw.getOngoing()) {
            System.out.println(DRAW_NOT_ONGOING);
            mainMenu.display();
            break;
          }

          buyTicketsPromptMenu.display();
          String buyTicketLine = sc.nextLine();
          String[] splitBuyTicketLine = buyTicketLine.split(",");
          String[] trimmedLine =
              Arrays.stream(splitBuyTicketLine).map(String::trim).toArray(String[]::new);
          // TODO: Exception?
          if (trimmedLine.length != 2) {
            System.out.println(BUY_TICKET_INPUT_WRONG);
            mainMenu.display();
          }
          try {
            String name = trimmedLine[0];
            int numTicketsToBuy = parseInt(trimmedLine[1]);
            Player player = draw.addPlayer(name);
            draw.buyTickets(numTicketsToBuy, player);
            mainMenu.updateOngoingStatus(draw.getDrawPool());

            PurchasedTicketsMenu purchasedTicketsMenu =
                new PurchasedTicketsMenu(player, numTicketsToBuy);
            purchasedTicketsMenu.display();
          } catch (NumberFormatException e) {
            System.out.println(
                "Can only buy integer number of tickets. Terminating transaction.\n");
          } catch (MaxTicketsExceededException | NegativeTicketsToBuyException e) {
            System.out.println(e.getMessage());
          }
          mainMenu.display();
          break;
        case "3":
          // Cannot run raffle if draw not started.
          if (!draw.getOngoing()) {
            System.out.println(DRAW_NOT_ONGOING);
            mainMenu.display();
            break;
          }
          // TODO: Handle case where no tickets bought
          raffle.run();
          runRaffleMenu.display();
          draw.end();
          mainMenu.resetStatus();
          // Write to System.in to continue
          sc.next();
          break;
          // TODO: default causes issue?
        default:
          // TODO: Cleanup
          System.out.println("Wrong input, please try again.\n");
          mainMenu.display();
          break;
      }
    }
  }

  public static void main(String[] args) {
    RaffleApplication application = new RaffleApplication();
    application.run();
  }
}
