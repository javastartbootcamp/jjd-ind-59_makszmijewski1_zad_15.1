package pl.javastart.task;

import pl.javastart.task.comparators.FirstNameComparator;
import pl.javastart.task.comparators.LastNameComparator;
import pl.javastart.task.comparators.ResultComparator;

import java.util.*;

public class TournamentStats {
    List<Player> players = new ArrayList<>();
    private static final String EXIT_PROGRAM = "STOP";
    private static final int FIRSTNAME_COMPARE_OPTION = 1;
    private static final int LASTNAME_COMPARE_OPTION = 2;
    private static final int RESULT_COMPARE_OPTION = 3;
    private static final int ASCENDING = 1;
    private static final int DESCENDING = 2;

    void run(Scanner scanner) {
        loadAndSavePlayers(scanner);
        Comparator<Player> comparator = chooseComperator(scanner);
        players.sort(comparator);
        FileWriter fileWriter = new FileWriter();
        fileWriter.saveToFileStats(players);
    }

    private Comparator<Player> chooseComperator(Scanner scanner) {
        boolean exit = false;
        Comparator<Player> comparator = null;
        while (!exit) {
            System.out.println("Po jakim parametrze posortować? (1 - imię, 2 - nazwisko, 3 - wynik");
            int whichComparator = scanner.nextInt();
            scanner.nextLine();
            comparator = switch (whichComparator) {
                case FIRSTNAME_COMPARE_OPTION -> new FirstNameComparator();
                case LASTNAME_COMPARE_OPTION -> new LastNameComparator();
                case RESULT_COMPARE_OPTION -> new ResultComparator();
                default -> null;
            };
            if (comparator == null) {
                System.out.println("Niepoprawny wybór, spróbuj jeszcze raz");
            } else {
                exit = true;
            }
        }
        System.out.println("Sortować rosnąco czy malejąco? (1 - rosnąco, 2 - malejąco)");
        int ascendindOrDescending = scanner.nextInt();
        if (ascendindOrDescending == DESCENDING) {
            return comparator.reversed();
        } else {
            return comparator;
        }
    }

    private void loadAndSavePlayers(Scanner scanner) {
        boolean exit = true;
        String playerAsString = "";
        while (exit) {
            System.out.println("Podaj wynik kolejnego gracza (lub stop):");
            playerAsString = scanner.nextLine();
            if (playerAsString.equals(EXIT_PROGRAM)) {
                exit = false;
            } else {
                Player player = createPlayer(playerAsString);
                players.add(player);
            }
        }
    }

    private Player createPlayer(String player) {
        String[] playerValues = player.split(" ");
        return new Player(playerValues[0], playerValues[1], Integer.parseInt(playerValues[2]));
    }
}
