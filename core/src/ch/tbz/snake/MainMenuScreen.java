package ch.tbz.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
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

        Gdx.input.setInputProcessor(stage);

        TextButton playButton = new TextButton("Play", skin);
        TextButton optionButton = new TextButton("Options", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        playButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                parent.setScreen(parent.gameScreen);
            }
        });
        optionButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.debug("Test", "options");
            }
        });
        exitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(playButton);
        table.row();
        table.add(optionButton);
        table.row();
        table.add(exitButton);
        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act();
        stage.draw();
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