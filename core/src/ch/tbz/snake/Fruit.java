package ch.tbz.snake;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Fruit {

    int x, y;

    public Fruit() {
        x = (int)( Math.random() * GameScreen.ntiles);
        y = (int)( Math.random() * GameScreen.ntiles);
    }

    public void spawn() {
        x = (int)( Math.random() * GameScreen.ntiles);
        y = (int)( Math.random() * GameScreen.ntiles);
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect( x * GameScreen.tileSize, y * GameScreen.tileSize,GameScreen.tileSize, GameScreen.tileSize);
    }

    public void dispose() {
    }

}
