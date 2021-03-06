package ch.tbz.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    /**
     * @return List<Integer>
     */
    public List<Integer> getHighScores() {
        return scores;
    }

    /**
     * Saves the 10 best scores to the preferrences
     * @param score
     */
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
