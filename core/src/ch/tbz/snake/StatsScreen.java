package ch.tbz.snake;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class StatsScreen extends ExtendedScreen {


    StatsManager statsManager;

    public StatsScreen(SnakeGame parent) {
        super(parent);
        statsManager = new StatsManager();
    }

    @Override public void show() {
        TextButton backButton = new TextButton("BACK", skin);
        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                parent.setScreen(new MainMenuScreen(parent));
            }
        });
        table = new Table();
        table.setFillParent(true);
        table.center();

        statsManager.getHighScores().forEach(s ->{
            table.add(new Label(""+s,skin));
            table.row();
        });
        table.row();
        table.add(new Label("",skin));
        table.row();
        table.add(backButton);
        stage.addActor(table);

        super.show();

    }

    @Override public void render(float delta) {
        super.render(delta);
    }

    @Override public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override public void pause() {

    }

    @Override public void resume() {

    }

    @Override public void hide() {

    }

    public void dispose() {
    }
}
