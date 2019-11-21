package es.ucm.gdv.Logic;

import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.Sprite;

public class button {

    Sprite _sprite;
    Rect _position;
    Graphics _graphics;

    public button(Sprite sprite, Rect position, Graphics graphics){
        _sprite = sprite;
        _position = position;
        _graphics = graphics;
    }

    public void drawButton(){_sprite.draw(_graphics,_position);};
}
