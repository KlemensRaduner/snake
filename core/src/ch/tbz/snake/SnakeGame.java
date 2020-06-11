package ch.tbz.snake;

import com.badlogic.gdx.Game;

public class SnakeGame extends Game {

	MainMenuScreen mainMenuScreen;
	@Override
	public void create () {

		mainMenuScreen = new MainMenuScreen(this);

		setScreen(mainMenuScreen);
	}


}
