package ch.tbz.snake;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;
import java.util.ArrayList;

public class Snake {

    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
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

        segments.forEach(s -> {
            if (s.x == head.x && s.y == head.y) {
                alive = false;
                segments.remove(0);
                statsManager.saveHighScore(segments.size());
                return;
            }
        });

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
        System.out.println(direction * 90);
        sprite.setRotation(direction * 90);
        batch.draw(sprite,xoffset+head.x * GameScreen.tileSize, yoffset+head.y * GameScreen.tileSize );
    }

    public void dispose() {
    }
}
