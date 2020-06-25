package ch.tbz.snake.desktop;

import ch.tbz.snake.GameScreen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ch.tbz.snake.SnakeGame;

public class DesktopLauncher {


	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = SnakeGame.WIDTH;
		config.height = SnakeGame.HEIGHT;
		new LwjglApplication(new SnakeGame(), config);
	}
}
