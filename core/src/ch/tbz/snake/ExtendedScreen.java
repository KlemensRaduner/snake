package ch.tbz.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class ExtendedScreen implements Screen {

    SpriteBatch batch;
    Stage stage;
    Viewport viewport;
    OrthographicCamera camera;
    TextureAtlas atlas;
    Skin skin;
    SnakeGame parent;
    Table table;


    public ExtendedScreen(SnakeGame parent) {
        this.parent = parent;
        atlas = new TextureAtlas("skin/vhs-ui.atlas");
        skin = new Skin(Gdx.files.internal("skin/vhs-ui.json"));
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(SnakeGame.WIDTH, SnakeGame.HEIGHT,camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, batch);
    }


    @Override
    public void render(float delta) {
        // clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void show() {
        // updates InputController
        // is here because it has to be called after the setScreen was called
        Gdx.input.setInputProcessor(new MenuInputController(stage));
    }

    @Override
    public void resize(int width, int height) {
        // updates viewport to fit into new window size
        viewport.update(width, height, true);
    }


    @Override
    public void dispose() {
        stage.dispose();
        atlas.dispose();
        batch.dispose();
        skin.dispose();
    }
}
