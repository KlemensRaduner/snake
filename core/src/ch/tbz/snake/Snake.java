package ch.tbz.snake;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;

public class Snake {

    public static final int UP = 0, DOWN = 2, LEFT = 1, RIGHT = 3;
    private GameScreen gameScreen;
    int direction;
    private Point head;
    private ArrayList<Point> segments;

    private Texture headTexture, bodyTexture;
    private Sprite headSprite;


    public Snake(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        direction = 0;
        head = new Point(GameScreen.ntiles / 2, GameScreen.ntiles / 2);
        segments = new ArrayList<>();


        bodyTexture = new Texture("snek/snakebody.png");
        headTexture = new Texture("snek/snakehead.png");
        headSprite = new Sprite(headTexture);
    }

    public int getLength(){
        return segments.size();
    }


    public void move() {
        segments.add(new Point(head.x, head.y));
        switch (direction) {
            case UP:
                head = new Point(head.x, head.y + 1);
                break;
            case DOWN:
                head = new Point(head.x, head.y - 1);
                break;
            case LEFT:
                head = new Point(head.x - 1, head.y);
                break;
            case RIGHT:
                head = new Point(head.x + 1, head.y);
                break;
        }

        if (head.x < 0 || head.x > GameScreen.ntiles - 1 || head.y < 0 || head.y > GameScreen.ntiles - 1) {
            gameScreen.endGame(segments.size()-1);
            gameScreen.statsManager.saveHighScore(segments.size()-1);
            return;
        }

       for(int i = 0;i<segments.size();i++){
            if (segments.get(i).x == head.x && segments.get(i).y == head.y) {
                gameScreen.endGame(segments.size()-1);
                gameScreen.statsManager.saveHighScore(segments.size()-1);
                return;
            }
        };

        if (gameScreen.fruit.x == head.x && gameScreen.fruit.y == head.y) {
            gameScreen.fruit.spawn();
        } else {
            segments.remove(0);
        }


    }

    public void draw(SpriteBatch batch , int xoffset, int yoffset){
        segments.forEach(s -> {
            Sprite sprite = new Sprite(bodyTexture);
            sprite.setPosition(xoffset + s.x * GameScreen.tileSize, yoffset +s.y * GameScreen.tileSize);
            sprite.draw(batch);
        });
        headSprite.setRotation(direction* 90);
        headSprite.setPosition(xoffset-1+head.x * GameScreen.tileSize, yoffset-1+head.y * GameScreen.tileSize);
        headSprite.draw(batch);
    }

    public void dispose() {
        bodyTexture.dispose();
        headTexture.dispose();
    }
}
