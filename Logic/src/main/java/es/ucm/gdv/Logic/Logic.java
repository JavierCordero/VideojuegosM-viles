package es.ucm.gdv.Logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.LogicInterface;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.ResourceManager;
import es.ucm.gdv.engine.Sprite;

import java.util.Vector;
import java.util.Map;

public class Logic implements LogicInterface {

    Game _game;
    ResourceManager _resourceManager;

    public void init(Game game){
        _game = game;
        _resourceManager = new ResourceManager(_game);

        _resourceManager.LoadImage("balls", _game.getGraphics().newImage("balls.png"));
        _resourceManager.createSpriteFromImage("balls", new Rect(0,128,0,128), "ball");
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public Boolean render() {
        _game.getGraphics().clear(0xFFF000FF);
        _resourceManager.getSprite("ball").draw(_game.getGraphics());
        return true;
    }
}
