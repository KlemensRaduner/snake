package ch.tbz.snake;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class SnakeGame extends Game {

	MainMenuScreen mainMenuScreen;
	@Override
	public void create () {

		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		mainMenuScreen = new MainMenuScreen(this);

		setScreen(mainMenuScreen);
	}


}
