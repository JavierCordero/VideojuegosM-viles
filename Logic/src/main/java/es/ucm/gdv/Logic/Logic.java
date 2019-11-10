package es.ucm.gdv.Logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Input;
import es.ucm.gdv.engine.LogicInterface;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.ResourceManager;
import es.ucm.gdv.engine.Sprite;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.Vector;
import java.util.Map;

public class Logic implements LogicInterface {

    Game _game;
    ResourceManager _rM;
    int colorMatch;
    String[] BGcolors = {"greenBG", "cyanBG", "blueBG", "darkBlueBG", "purpleBG", "greyBG", "orangeBG", "redBG", "browBG"};

    public void init(Game game){
        _game = game;
        _rM = new ResourceManager(_game);

        _rM.LoadImage("players", _game.getGraphics().newImage("players.png"));
        _rM.LoadImage("backgrounds", _game.getGraphics().newImage("backgrounds.png"));

        _rM.createSpriteFromImage("players",
                                                new Rect(0,528,0,384/2),
                                                "whitePlayer");

        _rM.createSpriteFromImage("players",
                                                new Rect(0,528,384/2,384),
                                                "blackPlayer");

        for(int i = 0; i < BGcolors.length; i++){
            _rM.createSpriteFromImage("backgrounds",
                    new Rect((288/9)*i,(288/9)*(i+1),0,32),
                    BGcolors[i]);
        }
        colorMatch = (int) (Math.random() * BGcolors.length-1) + 1;
    }

    @Override
    public void update(float deltaTime) {
    }

    @Override
    public Boolean render() {


        Graphics G = _game.getGraphics();
        G.clear(0xFF000000);
        _rM.getSprite(BGcolors[colorMatch]).draw(G,   new Rect(0,1080,0,1920));
        _rM.getSprite("whitePlayer").draw(G,
                new Rect((G.getWidth()/2)-_rM.getSprite("whitePlayer").getSpriteWidth()/2,
                        (G.getWidth()/2)+_rM.getSprite("whitePlayer").getSpriteWidth()/2,
                        1200-_rM.getSprite("whitePlayer").getSpriteHeight()/2,
                        1200+_rM.getSprite("whitePlayer").getSpriteHeight()/2));
       // _resourceManager.getSprite("whitePlayer").draw(_game.getGraphics(),   new Rect(540-264,540+264,1200,1397));


        return true;
    }
}
