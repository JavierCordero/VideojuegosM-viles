package es.ucm.gdv.Logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.LogicInterface;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.ResourceManager;
import es.ucm.gdv.engine.State;
import es.ucm.gdv.engine.StatesManager;

import java.util.HashMap;
import java.util.Map;

public class Logic implements LogicInterface {

    Game _game;
    ResourceManager _rM;

    StatesManager _statesManager;
    String[] BGcolors = {"greenBG", "cyanBG", "blueBG", "darkBlueBG", "purpleBG", "greyBG", "orangeBG", "redBG", "browBG"};
    String[] buttonsNames = {"interrogation", "exit", "sound", "mute", "home", "star", "dollar", "settings", "maximize", "shop"};

    public void init(Game game){
       _game = game;
       _rM = new ResourceManager(_game);

       loadImages();

       createSprites();

       _statesManager = new StatesManager(_game);

       State playState = new playState(_statesManager, _rM);
       _statesManager.addState(playState, "playState");

        State instrState = new instructionsState(_statesManager, _rM);
        _statesManager.addState(instrState, "instrState");

       State mainMenuState = new mainMenuState(_statesManager, _rM);
       _statesManager.addState(mainMenuState, "mainMenuState");

       _statesManager.chState("mainMenuState");

    }

    void loadImages(){
        _rM.LoadImage("tapToPlay", _game.getGraphics().newImage("tapToPlay.png"));
        _rM.LoadImage("backgrounds", _game.getGraphics().newImage("backgrounds.png"));
        _rM.LoadImage("arrowsBackground", _game.getGraphics().newImage("arrowsBackground.png"));
        _rM.LoadImage("howToPlay", _game.getGraphics().newImage("howToPlay.png"));
        _rM.LoadImage("instructions", _game.getGraphics().newImage("instructions.png"));
        _rM.LoadImage("arrowsBackground", _game.getGraphics().newImage("arrowsBackground.png"));
        _rM.LoadImage("logo", _game.getGraphics().newImage("switchDashLogo.png"));
        _rM.LoadImage("players", _game.getGraphics().newImage("players.png"));
        _rM.LoadImage("balls", _game.getGraphics().newImage("balls.png"));
        _rM.LoadImage("buttons", _game.getGraphics().newImage("buttons.png"));
    }

    void createSprites(){

        Image tapToPlay = _rM.getImage("tapToPlay");

        _rM.createSpriteFromImage("tapToPlay",
                new Rect(0,tapToPlay.getWidth(),0,tapToPlay.getHeight()),
                "ToPlay");

        Image howToPlay =  _rM.getImage("howToPlay");

        _rM.createSpriteFromImage("howToPlay",
                new Rect(0,howToPlay.getWidth(),0,howToPlay.getHeight()),
                "howToPlay");

        Image instructions = _rM.getImage("instructions");

        _rM.createSpriteFromImage("instructions",
                new Rect(0,instructions.getWidth(),0,instructions.getHeight()),
                "instructions");

        Image flechas = _rM.getImage("arrowsBackground");

        _rM.createSpriteFromImage("arrowsBackground",
                new Rect(0, flechas.getWidth(), 0, flechas.getHeight()),
                "BGArrow1");
        _rM.createSpriteFromImage("arrowsBackground",
                new Rect(0, flechas.getWidth(), 0, flechas.getHeight()),
                "BGArrow2");

        Graphics G = _game.getGraphics();

        _rM.getSprite("BGArrow1").set_destRect(new Rect((G.getWidth()/3)-_rM.getSprite("BGArrow1").getSpriteWidth()/3,
                (G.getWidth()/3)*2+_rM.getSprite("BGArrow1").getSpriteWidth()/3,
                0,
                G.getHeight()));

        _rM.getSprite("BGArrow2").set_destRect(new Rect((G.getWidth()/3)-_rM.getSprite("BGArrow2").getSpriteWidth()/3,
                (G.getWidth()/3)*2+_rM.getSprite("BGArrow2").getSpriteWidth()/3,
                -G.getHeight(),
                0));

        Image logo =  _rM.getImage("logo");

        _rM.createSpriteFromImage("logo",
                new Rect(0,logo.getWidth(),0,logo.getHeight()),
                "logo");

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

        for(int i = 0; i < buttonsNames.length; i++){
            _rM.createSpriteFromImage("buttons",
                    new Rect((1400/10)*i,(1400/10)*(i+1),0,140),
                    buttonsNames[i]);
        }

        Image balls = _rM.getImage("balls");

        _rM.createSpriteFromImage("balls",
                new Rect(0, 1280 / 10, 0, balls.getHeight() / 2),
                "whiteBall");

        _rM.createSpriteFromImage("balls",
                new Rect(0, 1280 / 10, balls.getHeight() / 2, balls.getHeight()),
                "blackBall");
    }

    @Override
    public void update(float deltaTime) {
        _statesManager.getActualState().update(deltaTime);
    }

    @Override
    public Boolean render() {
        _statesManager.getActualState().render();
        return true;
    }


}
