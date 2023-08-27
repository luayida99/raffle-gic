package sg.gic.com.menu;

import sg.gic.com.raffle.Raffle;

import java.util.StringJoiner;

public class RunRaffleMenu extends Menu {
    Raffle raffle;

    public RunRaffleMenu(Raffle raffle) {
        this.raffle = raffle;
    }

    @Override
    public String toString() {
        return this.raffle.toString();
    }
}
