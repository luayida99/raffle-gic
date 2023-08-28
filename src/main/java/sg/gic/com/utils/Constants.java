package sg.gic.com.utils;

/** Helper class used for storing constants to be used throughout application. */
public class Constants {
  public static final int DEFAULT_POOL = 100;
  public static final int MAX_TICKETS = 5;
  public static final int TICKET_NUM_NUMBERS = 5;
  public static final int TICKET_LOWER_BOUND = 1;
  public static final int TICKET_UPPER_BOUND = 15;
  public static final int JACKPOT_CATEGORY = 5;
  public static final int TICKET_COST = 5;

  public static final String WELCOME_MESSAGE = "Welcome to My Raffle App";
  public static final String NEW_RAFFLE_STARTED_MESSAGE =
      "New Raffle draw has been started. Initial pot size:";

  public static final String RETURN_TO_MAIN_MENU_MESSAGE = "Press any key to return to main menu";

  public static final String DRAW_NOT_STARTED_STATUS = "Draw has not started";
  public static final String DRAW_ONGOING_STATUS = "Draw is ongoing. Raffle pot size is";

  public static final String START_NEW_DRAW_OPTION = "Start a New Draw";
  public static final String BUY_TICKETS_OPTION = "Buy Tickets";
  public static final String RUN_RAFFLE_OPTION = "Run Raffle";

  public static final String BUY_TICKETS_PROMPT =
      "Enter your name, no of tickets to purchase (for e.g. a valid "
          + "input will be **James,1**)";
  public static final String RUN_RAFFLE_MESSAGE = "Running Raffle..";

  public static final String MAX_TICKETS_EXCEEDED_EXCEPTION =
      "Sorry, you are only allowed to buy %s tickets in total. You currently have "
          .formatted(MAX_TICKETS);
  public static final String NEGATIVE_TICKETS_EXCEPTION =
      "You cannot buy negative number of tickets.";
  public static final String BUY_TICKET_INPUT_WRONG = "Input for buying ticket is wrong.";
  public static final String DRAW_NOT_ONGOING = "Draw not currently ongoing. Please start one.";
  public static final String WRONG_INPUT = "Wrong input, please try again.";
  public static final String NON_INTEGER_TICKETS_PURCHASE = "Can only buy integer number of tickets. Terminating transaction.";
}
