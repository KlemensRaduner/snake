package ch.tbz.snake;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class SnakeGame extends Game {

	MainMenuScreen mainMenuScreen;
	ResumeScreen resumeMenuScreen;
	StatsScreen statsScreen;
	GameScreen gameScreen;

	StatsManager statsManager;


	@Override
	public void create () {

		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		mainMenuScreen = new MainMenuScreen(this);
		gameScreen = new GameScreen(this);
		resumeMenuScreen = new ResumeScreen(this);
		statsScreen = new StatsScreen(this);

		statsManager = new StatsManager();

		setScreen(mainMenuScreen);
	}


}
