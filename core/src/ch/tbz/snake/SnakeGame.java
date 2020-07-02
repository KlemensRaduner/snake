package ch.tbz.snake;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class SnakeGame extends Game {

	// initial window size in pixel
	public static int WIDTH = GameScreen.tileSize * (GameScreen.ntiles +2);
	public static int HEIGHT = GameScreen.tileSize * (GameScreen.ntiles +3);

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		setScreen(new MainMenuScreen(this));
	}


}
