package ch.tbz.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class MainMenuScreen extends ExtendedScreen {


    public MainMenuScreen(SnakeGame parent) {
        super(parent);
    }

    @Override
    public void show() {
        TextButton playButton = new TextButton("Play", skin);
        TextButton statsButton = new TextButton("Stats", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        playButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                parent.setScreen(new GameScreen(parent));
            }
        });
        statsButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                parent.setScreen(new StatsScreen(parent));
            }
        });
        exitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(playButton);
        table.row();
        table.add(statsButton);
        table.row();
        table.add(exitButton);
        stage.addActor(table);
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
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
        super.dispose();
    }


}