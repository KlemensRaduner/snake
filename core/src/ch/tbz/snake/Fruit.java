package ch.tbz.snake;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.List;

public class Fruit {

    int x, y;
    int frame;
    private List<Texture> textureList;
    private TextureRegion[][] textureRegions;
    private Sprite sprite;

    public Fruit() {
        // load all textures and store them in a list
        textureList = new ArrayList<Texture>();
        textureList.add(new Texture("fruits/mouse.png"));
        textureList.add(new Texture("fruits/apple.png"));
        textureList.add(new Texture("fruits/Burgitoo.png"));
        textureList.add(new Texture("fruits/cookie.png"));
        textureList.add(new Texture("fruits/ei.png"));
        textureList.add(new Texture("fruits/möffin.png"));
        textureList.add(new Texture("fruits/wine.png"));
        textureList.add(new Texture("fruits/iscriim.png"));
        textureList.add(new Texture("fruits/erdbeerli.png"));
        textureList.add(new Texture("fruits/cherry.png"));
        textureRegions = TextureRegion.split(textureList.get(0), 16, 16);
        sprite = new Sprite();
        spawn();


        // start animation timer
        Timer.schedule(new Timer.Task() {
            @Override public void run() {
                frame++;
                if (frame > 1) {
                    frame = 0;
                }
                sprite.setRegion(textureRegions[0][frame]);
            }
        }, 0, 0.5f);
    }

    public void spawn() {
        // set Sprite to random position with random Texture
        x = (int) (Math.random() * GameScreen.ntiles);
        y = (int) (Math.random() * GameScreen.ntiles);
        textureRegions = TextureRegion.split(textureList.get((int) (Math.random() * textureList.size())), 16, 16);
        sprite.setRegion(textureRegions[0][frame]);
    }


    public void draw(SpriteBatch batch, int xoffset, int yoffset) {
        batch.draw(sprite, xoffset + x * GameScreen.tileSize, yoffset + y * GameScreen.tileSize);
    }


    public void dispose() {
        textureList.forEach(texture -> texture.dispose());
    }

}
