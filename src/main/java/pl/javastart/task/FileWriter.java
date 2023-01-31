package pl.javastart.task;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriter {
    public void saveToFileStats(TournamentStats tournamentStats) {
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter("stats.csv"))) {
            for (Player player : tournamentStats.getPlayers()) {
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
