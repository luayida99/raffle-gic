package sg.gic.com.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.gic.com.player.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PurchasedTicketsMenuTest {
    private PurchasedTicketsMenu menu;

    @BeforeEach
    void setUp() {
        menu = new PurchasedTicketsMenu(new Player("John"), 1);
    }

    @Test
    @DisplayName("toString works as expected")
    void toStringTest() {
        String menuString = "Hi John, you have purchased 1 ticket(s) - \n";

        assertEquals(menuString, menu.toString());
    }
}
