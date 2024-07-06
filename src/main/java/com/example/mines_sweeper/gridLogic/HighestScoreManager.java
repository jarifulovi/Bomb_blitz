package com.example.mines_sweeper.gridLogic;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.mines_sweeper.gridLogic.logic.SCORE_DIRECTORY;

public class HighestScoreManager {


    public void saveHighestScore(String name,int level,double time){

        String filePath = SCORE_DIRECTORY + "highestScore" + level + ".txt";

        List<String> scores = readScoresFromFile(filePath);

        // Add the score if highest one
        if (shouldAddScore(scores, time)) {
            scores.add(formatScoreEntry(name, time));
        }


        // Sort scores based on time (lower time is better)
        if(scores.size() > 1) {
            Collections.sort(scores, (s1, s2) -> {
                double time1 = Double.parseDouble(s1.split(" ")[1]);
                double time2 = Double.parseDouble(s2.split(" ")[1]);
                return Double.compare(time1, time2);
            });
        }

        writeScoresToFile(filePath,scores);
    }
    // Method to read existing scores from a file
    private List<String> readScoresFromFile(String fileName) {
        List<String> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(line);
            }
        } catch (IOException e) {
            // Handle file IO exception
            e.printStackTrace();
        }
        return scores;
    }

    // Method to determine if a score should be added based on the top 10 criterion
    private boolean shouldAddScore(List<String> scores, double newTime) {
        // Check if the new score is among the top 10 or better than any existing score
        if (scores.size() < 10) {
            return true; // Add if less than 10 scores
        }

        // Compare with the 10th score
        double tenthScore = Double.parseDouble(scores.get(9).split(" ")[1]);
        return newTime < tenthScore;
    }

    // Method to format a score entry as "name time"
    private String formatScoreEntry(String name, double time) {
        return name + " " + String.format("%.2f", time);
    }

    // Method to write scores to a file
    private void writeScoresToFile(String fileName, List<String> scores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            int length = Math.min(scores.size(),10);

            for (int i=0;i<length;i++) {
                writer.write(scores.get(i));
                writer.newLine();
            }
        } catch (IOException e) {
            // Handle file IO exception
            e.printStackTrace();
        }
    }

    public static void main(String[] args){

        HighestScoreManager highestScoreManager = new HighestScoreManager();

        highestScoreManager.saveHighestScore("player7",1,11.7493);
        highestScoreManager.saveHighestScore("player7",1,13.4493);
        highestScoreManager.saveHighestScore("player7",1,26.7493);
        highestScoreManager.saveHighestScore("player7",1,29.7493);

    }
}
