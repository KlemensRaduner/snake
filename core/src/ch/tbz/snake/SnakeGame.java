package ch.tbz.snake;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class SnakeGame extends Game {

	public static int WIDTH = GameScreen.tileSize * (GameScreen.ntiles +2);
	public static int HEIGHT = GameScreen.tileSize * (GameScreen.ntiles +3);
	StatsManager statsManager;

	@Override
	public void create () {
		System.out.println(WIDTH+" "+HEIGHT);
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		statsManager = new StatsManager();

		setScreen(new MainMenuScreen(this));
	}


}
