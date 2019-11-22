package es.ucm.gdv.engine;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.Sprite;

public class Button {

    Sprite _sprite;
    Rect _position;
    Graphics _G;
    boolean pressed;

    public Button(Sprite sprite, Rect position, Graphics graphics){
        _sprite = sprite;
        _position = position;
        _G = graphics;
        pressed = true; //Porque al pulsarlo por primera vez cambiará a false
    }

    public void drawButton(){_sprite.draw(_G,_position);};

    public boolean buttonPressed(int x, int y){

        if(_G.mouseInsideRect(x,y, _position)){
            pressed = !pressed;
            return true;
        }
        return false;
    }

    public void changeButtonSprite(Sprite s){
        _sprite = s;
    }

    public boolean getPressed(){
        return pressed;
    }

}
