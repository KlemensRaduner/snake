package ch.tbz.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class GameScreen extends ExtendedScreen {

    public static final int ntiles = 25;
    public static final int tileSize = 16;
    Snake snake;
    Fruit fruit;
    float time;
    ShapeRenderer shapeRenderer;

    public GameScreen(SnakeGame parent) {
        super(parent);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.translate(0,tileSize,0);


        fruit = new Fruit();
        snake = new Snake(fruit);
    }

    @Override
    public void show(){
        super.show();

        Label label = new Label("Highscore",skin);
        label.setHeight(tileSize);
        label.setFontScale(0.5f);
        stage.addActor(label);
    }


    @Override
    public void render(float delta) {
        super.render(delta);

        if (Gdx.input.isKeyPressed(Input.Keys.UP) && snake.direction != Snake.DOWN) {
            snake.direction = Snake.UP;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && snake.direction != Snake.UP) {
            snake.direction = Snake.DOWN;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && snake.direction != Snake.LEFT) {
            snake.direction = Snake.RIGHT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && snake.direction != Snake.RIGHT) {
            snake.direction = Snake.LEFT;
        }


        time += delta;
        if (time >= 0.1) {
            if (snake.alive) {
                snake.move();
            }
            time = 0;
        }


        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        snake.draw(shapeRenderer);
        fruit.draw(shapeRenderer);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(0,0,ntiles * tileSize, ntiles *tileSize);
        shapeRenderer.end();

        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        snake.dispose();
        fruit.dispose();
    }
}
