package ch.tbz.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StatsManager {

    Preferences prefs = Gdx.app.getPreferences("snake");

    private List<Integer> scores = new ArrayList<>();

    public StatsManager() {
        String text = prefs.getString("highscores");
        String[] csv = text.split(",");
        for (String s : csv) {
            try {
                scores.add(Integer.parseInt(s));
            } catch (Exception e) {
            }
        }
    }

    public List<Integer> getHighScores() {
        return scores;
    }

    public void saveHighScore(int score) {
        scores.add(score);
        Collections.sort(scores, Collections.reverseOrder());
        if (scores.size() > 10) {
            scores.subList(10, scores.size()).clear();
        }
        StringBuilder csvBuilder = new StringBuilder();
        for (int s : scores) {
            csvBuilder.append(s);
            csvBuilder.append(",");
        }
        prefs.putString("highscores", csvBuilder.toString());
        prefs.flush();
    }

}
