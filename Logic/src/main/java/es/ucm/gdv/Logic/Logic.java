package es.ucm.gdv.Logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.LogicInterface;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.Sprite;

import java.util.Vector;
import java.util.Map;

public class Logic implements LogicInterface {

    Game _game;
    Map<String, Sprite> _sprites;
    Map<String, Image> _images;

    public void init(Game game){
        _game = game;
    }

    public void loadImage(String str){
        _images.put(str, _game.getGraphics().newImage(str));
    }

    public void createSpriteFromImage(Image img, Rect src, String name){
        _sprites.put(name, _game.getGraphics().createSprite(img, src));
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public Boolean render() {
        _game.getGraphics().clear(0xFFF000FF);
        return true;
    }
}
