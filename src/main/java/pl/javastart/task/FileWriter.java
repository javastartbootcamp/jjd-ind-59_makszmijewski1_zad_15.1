package pl.javastart.task;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class FileWriter {
    public void saveToFileStats(List<Player> players) {
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter("stats.csv"))) {
            for (Player player : players) {
                String playerAsString = playerAsString(player);
                writer.write(playerAsString + "\n");
            }
        } catch (IOException e) {
            System.out.println("Nie udało się zapisać statystyk zawodników");
        }
    }

    private String playerAsString(Player player) {
        return player.getFirstName() + " " + player.getLastName() + ";" + player.getResult();
    }
}
