package ch.tbz.snake;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;
import java.util.ArrayList;

public class Snake {

    public static final int UP = 0, DOWN = 2, LEFT = 1, RIGHT = 3;
    boolean alive;
    int direction;
    private Point head;
    private ArrayList<Point> segments;
    private Fruit fruit;

    private Texture texture;
    private Sprite sprite;

    private StatsManager statsManager;

    public Snake(Fruit fruit, StatsManager statsManager) {
        this.fruit = fruit;
        this.statsManager = statsManager;
        alive = true;
        direction = 0;
        head = new Point(GameScreen.ntiles / 2, GameScreen.ntiles / 2);
        segments = new ArrayList<>();

        texture = new Texture("snek/snakehead.png");
        sprite = new Sprite(texture);
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
            alive = false;
            segments.remove(0);
            statsManager.saveHighScore(segments.size());
            return;
        }

       for(int i = 0;i<segments.size();i++){
            if (segments.get(i).x == head.x && segments.get(i).y == head.y) {
                alive = false;
                segments.remove(0);
                statsManager.saveHighScore(segments.size());
                return;
            }
        };

        if (fruit.x == head.x && fruit.y == head.y) {
            fruit.spawn();
        } else {
            segments.remove(0);
        }


    }

    public void drawBody(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(14f/255, 209f/255,69f/255, 1);
        segments.forEach(s -> {
            shapeRenderer.rect(s.x * GameScreen.tileSize, s.y * GameScreen.tileSize, GameScreen.tileSize, GameScreen.tileSize);
        });

    }

    public void drawHead(SpriteBatch batch , int xoffset, int yoffset){
        sprite.setRotation(direction* 90);
        sprite.setPosition(xoffset-1+head.x * GameScreen.tileSize, yoffset-1+head.y * GameScreen.tileSize);
        sprite.draw(batch);
    }

    public void dispose() {
    }
}
