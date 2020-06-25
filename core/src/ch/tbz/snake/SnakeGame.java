package ch.tbz.snake;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class SnakeGame extends Game {

	StatsManager statsManager;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		statsManager = new StatsManager();

		setScreen(new MainMenuScreen(this));
	}


}
