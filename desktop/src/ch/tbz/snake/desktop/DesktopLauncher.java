package ch.tbz.snake.desktop;

import ch.tbz.snake.GameScreen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ch.tbz.snake.SnakeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameScreen.tileSize * (GameScreen.ntiles +2);
		config.height = GameScreen.tileSize * (GameScreen.ntiles + 3);
		new LwjglApplication(new SnakeGame(), config);
	}
}
