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

        _resourceManager.LoadImage("players", _game.getGraphics().newImage("players.png"));
        _resourceManager.LoadImage("backgrounds", _game.getGraphics().newImage("backgrounds.png"));

        _resourceManager.createSpriteFromImage("players",
                                                new Rect(0,528,0,384/2),
                                                "whitePlayer");

        _resourceManager.createSpriteFromImage("players",
                                                new Rect(0,528,384/2,384),
                                                "blackPlayer");

        _resourceManager.createSpriteFromImage("backgrounds",
                                                new Rect(0,288/9,0,32),
                                                "greenBackground");
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public Boolean render() {
        //_game.getGraphics().clear(0xFFF000FF); //Test para probar el clear, aqui lo dejo por ahora para que no se me olvide el color
        _resourceManager.getSprite("greenBackground").draw(_game.getGraphics(),   new Rect(0,1080,0,1920));
        /*_resourceManager.getSprite("whitePlayer").draw(_game.getGraphics(),
                new Rect((_game.getGraphics().getWidth()/2)-_resourceManager.getSprite("whitePlayer").getSpriteWidth()/2,
                        (_game.getGraphics().getWidth()/2)+_resourceManager.getSprite("whitePlayer").getSpriteWidth()/2,
                        1200-_resourceManager.getSprite("whitePlayer").getSpriteHeight()/2,
                        1200+_resourceManager.getSprite("whitePlayer").getSpriteHeight()/2));*/
        _resourceManager.getSprite("whitePlayer").draw(_game.getGraphics(),   new Rect(540-264,540+264,1200,1397));


        return true;
    }
}
