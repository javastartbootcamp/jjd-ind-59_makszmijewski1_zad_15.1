package pl.javastart.task.comparators;

import pl.javastart.task.Player;

import java.util.Comparator;

public class ResultComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        return Integer.compare(o1.getResult(), o2.getResult());
    }
}
