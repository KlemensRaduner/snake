package ch.tbz.snake;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;

public class Snake {

    public static final int UP = 0, LEFT = 1, DOWN = 2,  RIGHT = 3;
    private GameScreen gameScreen;
    int direction;
    private Point head;
    private ArrayList<Point> segments;

    private Texture headTexture, bodyTexture, tailTexture;
    private Sprite headSprite, tailSprite;


    public Snake(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        direction = 0;
        head = new Point(GameScreen.ntiles / 2, GameScreen.ntiles / 2);
        segments = new ArrayList<>();
        bodyTexture = new Texture("snek/snakebody.png");
        headTexture = new Texture("snek/snakehead.png");
        tailTexture = new Texture( "snek/snaketail.png");
        headSprite = new Sprite(headTexture);
        tailSprite = new Sprite(tailTexture);
    }

    /**
     * @return int
     */
    public int getLength(){
        return segments.size();
    }

    /**
     * moves the snake by adding a new segment at the old position of the head
     * and removing the last segment.
     */
    public void move() {
        // add a new segment at the head position
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

        // test if snake is in wall
        if (head.x < 0 || head.x > GameScreen.ntiles - 1 || head.y < 0 || head.y > GameScreen.ntiles - 1) {
            gameScreen.endGame(segments.size()-1);
            gameScreen.statsManager.saveHighScore(segments.size()-1);
            return;
        }

        // test if snake has eaten its tail
       for(int i = 0;i<segments.size();i++){
            if (segments.get(i).x == head.x && segments.get(i).y == head.y) {
                gameScreen.endGame(segments.size()-1);
                gameScreen.statsManager.saveHighScore(segments.size()-1);
                return;
            }
        };

       // test if fruit is eaten
        if (gameScreen.fruit.x == head.x && gameScreen.fruit.y == head.y) {
            gameScreen.fruit.spawn();
        } else {
            segments.remove(0);
        }


    }

    public void draw(SpriteBatch batch , int xoffset, int yoffset){

        // draw body
        for(int i = 1;i<segments.size();i++){
            Sprite sprite = new Sprite(bodyTexture);
            sprite.setPosition(xoffset + segments.get(i).x * GameScreen.tileSize, yoffset +segments.get(i).y * GameScreen.tileSize);
            sprite.draw(batch);
        }

        // draw head
        headSprite.setRotation(direction* 90);
        headSprite.setPosition(xoffset-1+head.x * GameScreen.tileSize, yoffset-1+head.y * GameScreen.tileSize);
        headSprite.draw(batch);

        // draw tail
        if(segments.size() > 0){
            if(segments.size() == 1){
                tailSprite.setRotation(direction* 90);
            }else {
                  int dx =  segments.get(1).x -segments.get(0).x;
                  int dy = segments.get(1).y - segments.get(0).y;
                switch (2*dx+dy){
                    case 1:
                        tailSprite.setRotation(UP *90);
                        break;
                    case 2:
                        tailSprite.setRotation(RIGHT *90);
                        break;
                    case -1:
                        tailSprite.setRotation(DOWN *90);
                        break;
                    case -2:
                        tailSprite.setRotation(LEFT *90);
                        break;
                }
            }
            tailSprite.setPosition(xoffset + segments.get(0).x * GameScreen.tileSize, yoffset +segments.get(0).y * GameScreen.tileSize);
            tailSprite.draw(batch);
        }

    }

    public void dispose() {
        tailTexture.dispose();
        bodyTexture.dispose();
        headTexture.dispose();
    }
}
