package es.ucm.gdv.Logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Input;
import es.ucm.gdv.engine.LogicInterface;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.ResourceManager;
import es.ucm.gdv.engine.Sprite;
import es.ucm.gdv.engine.State;
import es.ucm.gdv.engine.StatesManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logic implements LogicInterface {

    Game _game;
    ResourceManager _rM;
    Graphics _G;

    StatesManager _statesManager;
    String[] BGcolors = {"greenBG", "cyanBG", "blueBG", "darkBlueBG", "purpleBG", "greyBG", "orangeBG", "redBG", "browBG"};
    String[] buttonsNames = {"interrogation", "exit", "sound", "mute", "home", "star", "dollar", "settings", "maximize", "shop"};
    int [] colors = {0xff41a85f, 0xff00a885, 0xff3d8eb9, 0xff2969b0, 0xff553982, 0xff28324e, 0xfff37934, 0xffd14b41, 0xff75706b};

    public BehindColor _behindColor;
    Arrows _arrows;

    public class BehindColor{
        int currentColor;
        public void setCurrentColor(int c){ currentColor  = c;}
        public String [] getBGcolors(){return BGcolors;}
    }


    public void init(Game game){
       _game = game;
       _rM = new ResourceManager(_game);
       _G = _game.getGraphics();
       _statesManager = new StatesManager(_game);

       loadImages();
       createSprites();

       _arrows = new Arrows();
       _arrows.init(_rM, _G);

       _behindColor = new BehindColor();
       _behindColor.currentColor = 0;

       createStates();

       //Start game
       _statesManager.changeState("mainMenuState");

    }

    void createStates(){
        State playState = new playState(_statesManager, _rM, _arrows, _behindColor);
        playState.stateName = "playState";
        _statesManager.addState(playState, playState.stateName);

        State instrState = new instructionsState(_statesManager, _rM, _arrows, _behindColor);
        instrState.stateName = "instrState";
        _statesManager.addState(instrState, instrState.stateName);

        State mainMenuState = new mainMenuState(_statesManager, _rM, _arrows, _behindColor);
        mainMenuState.stateName = "mainMenuState";
        _statesManager.addState(mainMenuState,  mainMenuState.stateName);

        State endState = new endState(_statesManager, _rM, _arrows, _behindColor);
        endState.stateName = "endState";
        _statesManager.addState(endState, endState.stateName);
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
        _rM.LoadImage("gameOver", _game.getGraphics().newImage("gameOver.png"));
        _rM.LoadImage("playAgain", _game.getGraphics().newImage("playAgain.png"));
        _rM.LoadImage("font", _game.getGraphics().newImage("scoreFont.png"));
        _rM.LoadImage("white", _game.getGraphics().newImage("white.png"));
    }

    void createSprites(){

        Image tapToPlay = _rM.getImage("tapToPlay");

        _rM.createSpriteFromImage("tapToPlay",
                new Rect(0,tapToPlay.getWidth(),0,tapToPlay.getHeight()),
                "ToPlay", 255);

        Image howToPlay =  _rM.getImage("howToPlay");

        _rM.createSpriteFromImage("howToPlay",
                new Rect(0,howToPlay.getWidth(),0,howToPlay.getHeight()),
                "howToPlay", 255);

        Image instructions = _rM.getImage("instructions");

        _rM.createSpriteFromImage("instructions",
                new Rect(0,instructions.getWidth(),0,instructions.getHeight()),
                "instructions", 255);

        Image flechas = _rM.getImage("arrowsBackground");

        _rM.createSpriteFromImage("arrowsBackground",
                new Rect(0, flechas.getWidth(), 0, flechas.getHeight()),
                "BGArrow1", 70);
        _rM.createSpriteFromImage("arrowsBackground",
                new Rect(0, flechas.getWidth(), 0, flechas.getHeight()),
                "BGArrow2", 70);

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
                "logo", 255);

        _rM.createSpriteFromImage("players",
                new Rect(0,528,0,384/2),
                "whitePlayer", 255);

        _rM.createSpriteFromImage("players",
                new Rect(0,528,384/2,384),
                "blackPlayer", 255);

        for(int i = 0; i < BGcolors.length; i++){
            _rM.createSpriteFromImage("backgrounds",
                    new Rect((288/9)*i,(288/9)*(i+1),0,32),
                    BGcolors[i], 255);
        }

        for(int i = 0; i < buttonsNames.length; i++){
            _rM.createSpriteFromImage("buttons",
                    new Rect((1400/10)*i,(1400/10)*(i+1),0,140),
                    buttonsNames[i], 255);
        }

        Image balls = _rM.getImage("balls");

        _rM.createSpriteFromImage("balls",
                new Rect(0, 1280 / 10, 0, balls.getHeight() / 2),
                "whiteBall", 255);

        _rM.createSpriteFromImage("balls",
                new Rect(0, 1280 / 10, balls.getHeight() / 2, balls.getHeight()),
                "blackBall", 255);

        _rM.createSpriteFromImage("balls",
                new Rect(0, 1280 / 10, 0, balls.getHeight() / 2),
                "whiteParticle", 20);

        _rM.createSpriteFromImage("balls",
                new Rect(0, 1280 / 10, balls.getHeight() / 2, balls.getHeight()),
                "blackParticle", 20);

        Image gOver = _rM.getImage("gameOver");
        _rM.createSpriteFromImage("gameOver", new Rect(0, gOver.getWidth(), 0 , gOver.getHeight()),
                "gameOver",255);

        Image pAgain = _rM.getImage("playAgain");
        _rM.createSpriteFromImage("playAgain", new Rect(0, pAgain.getWidth(), 0 , pAgain.getHeight()),
                "playAgain",255);

        Image white = _rM.getImage("white");
        _rM.createSpriteFromImage("white", new Rect(0, white.getWidth(), 0 , white.getHeight()),
                "white",255);
        _rM.getSprite("white").set_destRect(new Rect(0, _G.getWidth(), 0, _G.getHeight()));

        //NUMBERS
        int j = 3;
        int index = 0;
        for(int i = 1; i <= 10; i++){

            _rM.createSpriteFromImage("font", new Rect((7 + index) * 125,
                    ((7 + index) * 125) + 125,
                            j * 160 ,
                            (j * 160) + 160),
                        "number" + ( i - 1)  ,255);

            index++;
            if(i == 8) {
                j++;
                index = -7;
            }
        }

        //LETTERS
        j = 0;
        index = 0;
        int letter = 97; //ascii de la a
        for(int i = 0; i < 26; i++){

            _rM.createSpriteFromImage("font", new Rect(index * 125,
                            ( index * 125) + 125,
                            j * 160 ,
                            (j * 160) + 160),
                    "letra" + (char)letter  ,255);

            index++;
            letter++;
            if(i ==14) {
                j++;
                index = 0;
            }
        }
    }

    @Override
    public void handleEvent(Input.TouchEvent event){
        _statesManager.getActualState().handleEvent(event);
    }

    @Override
    public void update(float deltaTime) {
        _statesManager.getActualState().update(deltaTime);
    }

    @Override
    public Boolean render() {
        _G.clear(0xFF000000); // Color negro para las bandas negras laterales.

        Rect r = new Rect(0, _G.getWidth(), 0, _G.getHeight());
        _G.drawColor(colors[_behindColor.currentColor], r);

        _statesManager.getActualState().render();

        //Comprobamos si hay algún estado al que tengamos que cambiar después del render
        //ya que es lo último a lo que el bucle ppal llama
        if(_statesManager.getQuedState() != null)
            _statesManager.changeState(_statesManager.getQuedState().stateName);
        return true;
    }
}
