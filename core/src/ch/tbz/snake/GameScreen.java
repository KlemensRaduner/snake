package ch.tbz.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;

public class GameScreen extends ExtendedScreen {

    public static final int ntiles = 25;
    public static final int tileSize = 16;
    Snake snake;
    Fruit fruit;
    float time;
    int startDelay;
    ShapeRenderer shapeRenderer;
    StatsManager statsManager;

    Label scoreLabel;
    Label timerLabel;

    public GameScreen(SnakeGame parent) {
        super(parent);
        statsManager = new StatsManager();

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.translate(tileSize, tileSize * 2, 0);


        fruit = new Fruit();
        snake = new Snake(fruit);
    }

    @Override
    public void show() {
        super.show();

        timerLabel = new Label("3",skin);
        timerLabel.setHeight(tileSize*3);
        timerLabel.setPosition(tileSize * ((ntiles / 2)+1), tileSize * ((ntiles / 2)+1));
        stage.addActor(timerLabel);

        startDelay = 3;
        scoreLabel = new Label("SCORE: " + 0, skin);
        scoreLabel.setHeight(tileSize);
        scoreLabel.setFontScale(0.5f);
        scoreLabel.setPosition(tileSize * ntiles / 2, tileSize * 0.5f);
        stage.addActor(scoreLabel);

        Label label = new Label("HIGHSCORE: " + statsManager.getHighScores().get(0), skin);
        label.setHeight(tileSize);
        label.setFontScale(0.5f);
        label.setPosition(tileSize, tileSize * 0.5f);
        stage.addActor(label);

        Timer.schedule(new Timer.Task() {
            @Override public void run() {
                startDelay--;
                timerLabel.setText(startDelay);
            }
        },1,1,2);
    }


    @Override
    public void render(float delta) {
        super.render(delta);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(0, 0, ntiles * tileSize, ntiles * tileSize);
        shapeRenderer.end();

        if(startDelay > 0 || !snake.alive){
            return;
        }
        timerLabel.setText("");

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

        batch.begin();
        fruit.draw(batch,tileSize, tileSize * 2);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        snake.draw(shapeRenderer);
       // fruit.draw(shapeRenderer);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.end();
        scoreLabel.setText("SCORE: " + snake.getLength());
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
