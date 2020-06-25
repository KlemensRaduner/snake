package ch.tbz.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class ExtendedScreen implements Screen {

    SpriteBatch batch;
    Stage stage;
    Viewport viewport;
    OrthographicCamera camera;
    TextureAtlas atlas;
    Skin skin;
    SnakeGame parent;


    public ExtendedScreen(SnakeGame parent) {
        this.parent = parent;
        atlas = new TextureAtlas("skin/vhs-ui.atlas");
        skin = new Skin(Gdx.files.internal("skin/vhs-ui.json"));
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FillViewport(SnakeGame.WIDTH, SnakeGame.HEIGHT);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, batch);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }


    @Override
    public void dispose() {
        stage.dispose();
        atlas.dispose();
        batch.dispose();
        skin.dispose();
    }
}
