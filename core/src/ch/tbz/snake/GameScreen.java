package ch.tbz.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;


public class GameScreen extends ExtendedScreen {

    private static final int STARTING = 0;
    private static final int RUNNING = 1;
    private static final int PAUSE = 2;
    public static int ntiles = 25;
    public static int tileSize = 16;
    int state;

    Snake snake;
    Fruit fruit;
    float time;
    int startDelay;
    ShapeRenderer shapeRenderer;
    StatsManager statsManager;

    Label scoreLabel;
    Label timerLabel;
    Label pauseLabel;

    public GameScreen(SnakeGame parent) {
        super(parent);
        statsManager = new StatsManager();

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.translate(tileSize, tileSize * 2, 0);

        fruit = new Fruit();
        snake = new Snake(this);

        state = STARTING;
    }

    @Override
    public void show() {
        super.show();

        pauseLabel = new Label("", skin);
        pauseLabel.setHeight(tileSize * 3);
        pauseLabel.setPosition(tileSize * ((ntiles / 2) - 1.5f), tileSize * ((ntiles / 2) + 1));
        stage.addActor(pauseLabel);

        timerLabel = new Label("3", skin);
        timerLabel.setHeight(tileSize * 3);
        timerLabel.setPosition(tileSize * ((ntiles / 2) + 1), tileSize * ((ntiles / 2) + 1));
        stage.addActor(timerLabel);

        startDelay = 3;
        scoreLabel = new Label("SCORE: " + 0, skin);
        scoreLabel.setHeight(tileSize);
        scoreLabel.setFontScale(0.5f);
        scoreLabel.setPosition(tileSize * ntiles / 2, tileSize * 0.5f);
        stage.addActor(scoreLabel);

        Label label = new Label("HIGHSCORE: " + (statsManager.getHighScores().size() > 0 ? statsManager.getHighScores().get(0) : 0), skin);
        label.setHeight(tileSize);
        label.setFontScale(0.5f);
        label.setPosition(tileSize, tileSize * 0.5f);
        stage.addActor(label);

        Timer.schedule(new Timer.Task() {
            @Override public void run() {
                startDelay--;
                timerLabel.setText(startDelay);
                if (startDelay == 0) {
                    state = RUNNING;
                }
            }
        }, 1, 1, 2);
    }

    public void endGame(int score) {
        parent.setScreen(new GameOverScreen(parent, score));
    }

    public void drawEdge() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(0, 0, ntiles * tileSize, ntiles * tileSize);
        shapeRenderer.end();
    }

    private void drawGame() {
        batch.begin();
        fruit.draw(batch, tileSize, tileSize * 2);
        snake.draw(batch, tileSize, tileSize * 2);
        batch.end();

        scoreLabel.setText("SCORE: " + (snake.getLength()));
        stage.draw();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        time += delta;
        drawEdge();

        switch (state) {
            case PAUSE:
                pauseLabel.setText("PAUSE");
                if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && time > 0.2) {
                    state = RUNNING;
                    pauseLabel.setText("");
                    time = 0;
                }
                drawGame();
                break;
            case RUNNING:
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
                if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && time >= 0.1) {
                    state = PAUSE;
                    time = 0;
                    break;
                }


                if (time >= 0.1) {
                    snake.move();
                    time = 0;
                }

                drawGame();
            default:
        }
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
