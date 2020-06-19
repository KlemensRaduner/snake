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
        textureList = new ArrayList<Texture>();
        textureList.add(new Texture("fruits/mouse.png"));
        textureList.add(new Texture("fruits/apple.png"));
        textureList.add(new Texture("fruits/Burgitoo.png"));
        textureRegions = TextureRegion.split(textureList.get(0), 16, 16);
        sprite = new Sprite(textureRegions[0][0]);
        x = (int) (Math.random() * GameScreen.ntiles);
        y = (int) (Math.random() * GameScreen.ntiles);

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
        x = (int) (Math.random() * GameScreen.ntiles);
        y = (int) (Math.random() * GameScreen.ntiles);
        textureRegions = TextureRegion.split(textureList.get((int) (Math.random() * textureList.size())), 16, 16);
        sprite.setRegion(textureRegions[0][frame]);
    }


    public void draw(SpriteBatch batch, int xoffset, int yoffset) {
        batch.draw(sprite, xoffset + x * GameScreen.tileSize, yoffset + y * GameScreen.tileSize);
    }

    /*
    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect( x * GameScreen.tileSize, y * GameScreen.tileSize,GameScreen.tileSize, GameScreen.tileSize);
    }
     */

    public void dispose() {
    }

}
