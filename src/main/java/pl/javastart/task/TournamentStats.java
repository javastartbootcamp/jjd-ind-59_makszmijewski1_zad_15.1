package pl.javastart.task;

import pl.javastart.task.comparators.FirstNameComparator;
import pl.javastart.task.comparators.LastNameComparator;
import pl.javastart.task.comparators.ResultComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TournamentStats {
    List<Player> players = new ArrayList<>();
    private static final String EXIT_PROGRAM = "STOP";
    private static final int FIRSTNAME_COMPARE_OPTION = 1;
    private static final int LASTNAME_COMPARE_OPTION = 2;
    private static final int RESULT_COMPARE_OPTION = 3;
    private static final int ASCENDING = 1;
    private static final int DESCENDING = 2;

    public List<Player> getPlayers() {
        return players;
    }

    void run(Scanner scanner) {
        loadAndSavePlayers(scanner);
        sortPlayers(scanner);
        FileWriter fileWriter = new FileWriter();
        fileWriter.saveToFileStats(this);
    }

    private void sortPlayers(Scanner scanner) {
        System.out.println("Po jakim parametrze posortować? (1 - imię, 2 - nazwisko, 3 - wynik");
        int whichComparator = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Sortować rosnąco czy malejąco? (1 - rosnąco, 2 - malejąco)");
        int ascendingOrDescending = scanner.nextInt();
        scanner.nextLine();
        switch (whichComparator) {
            case FIRSTNAME_COMPARE_OPTION -> {
                if (ascendingOrDescending == ASCENDING) {
                    players.sort(new FirstNameComparator());
                } else {
                    players.sort(new FirstNameComparator().reversed());
                }
            }
            case LASTNAME_COMPARE_OPTION -> {
                if (ascendingOrDescending == ASCENDING) {
                    players.sort(new LastNameComparator());
                } else {
                    players.sort(new LastNameComparator().reversed());
                }
            }
            case RESULT_COMPARE_OPTION -> {
                if (ascendingOrDescending == ASCENDING) {
                    players.sort(new ResultComparator());
                } else {
                    players.sort(new ResultComparator().reversed());
                }
            }
            default -> {
                System.out.println("Nie udało się posortować");
            }
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
