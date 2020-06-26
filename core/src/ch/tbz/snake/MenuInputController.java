package ch.tbz.snake;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;


public class MenuInputController implements InputProcessor {

    Stage stage;
    int index;
    List<Button> buttons = new ArrayList<>();

    public MenuInputController(Stage stage) {
        this.stage  = stage;
        stage.getActors().forEach(a ->{
            buttons.addAll(getButtonsFromActor(a));
        });
        if(buttons.size()>0){
            setFocus(buttons.get(0));
        }
    }

    public List<Button> getButtonsFromActor(Actor actor){
        ArrayList<Button> b = new ArrayList<>();
        if(actor instanceof Table){
            Table t = (Table) actor;
            if(t.getChildren().size>0){
                t.getChildren().forEach(c ->{
                    b.addAll(getButtonsFromActor(c));
                });
            }
            if(actor instanceof Button){
                b.add((Button) actor);
            }
        }
        return b;
    }


    public void removeFocus(Button b) {
        InputEvent event = new InputEvent();
        event.setType(InputEvent.Type.exit);
        event.setPointer(-1);
        b.fire(event);
    }

    public void setFocus(Button b) {
        InputEvent event = new InputEvent();
        event.setType(InputEvent.Type.enter);
        event.setPointer(-1);
        b.fire(event);
    }

    public void clickButton(Button b) {
        Array<EventListener> listeners = b.getListeners();
        for(int i=0;i<listeners.size;i++)
        {
            if(listeners.get(i) instanceof ClickListener){
                ((ClickListener)listeners.get(i)).clicked(null, 0, 0);
            }
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if (buttons.size() > 0) {


            if (keycode == Input.Keys.DOWN) {

                removeFocus(buttons.get(index));

                index++;
                if (index >= buttons.size()) {
                    index = 0;
                }

                setFocus(buttons.get(index));

            } else if (keycode == Input.Keys.UP) {
                removeFocus(buttons.get(index));

                index--;
                if (index < 0) {
                    index = buttons.size() - 1;
                }

                setFocus(buttons.get(index));

            } else if (keycode == Input.Keys.SPACE) {
                clickButton(buttons.get(index));
            }


        }
        return false;
    }

    @Override public boolean keyUp(int keycode) {
        return false;
    }

    @Override public boolean keyTyped(char character) {
        return false;
    }

    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override public boolean scrolled(int amount) {
        return false;
    }
}
