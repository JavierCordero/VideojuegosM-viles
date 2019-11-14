package es.ucm.gdv.Logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.LogicInterface;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.ResourceManager;
import es.ucm.gdv.engine.Sprite;
import es.ucm.gdv.engine.State;
import es.ucm.gdv.engine.StatesManager;

import java.util.HashMap;
import java.util.Map;

public class Logic implements LogicInterface {

    Game _game;
    ResourceManager _rM;
    Graphics _G;

    StatesManager _statesManager;
    String[] BGcolors = {"greenBG", "cyanBG", "blueBG", "darkBlueBG", "purpleBG", "greyBG", "orangeBG", "redBG", "browBG"};
    String[] buttonsNames = {"interrogation", "exit", "sound", "mute", "home", "star", "dollar", "settings", "maximize", "shop"};

    int BGspeed = 384;

    public class BehindBars{
        int prioridad = 0;

        public void changePriority(){
            if(prioridad == 0)
                prioridad = 1;
            else prioridad = 0;
        }

        public int getPriority(){
            return prioridad;
        }

        public void draw(float deltaTime){
            Sprite backArrow = _rM.getSprite("BGArrow1");
            Rect bacArrowRect = backArrow.get_destRect();

            Sprite backArrow2 = _rM.getSprite("BGArrow2");
            Rect bacArrowRect2 = backArrow2.get_destRect();

            if(bacArrowRect.get_top() >= _G.getHeight()) {
                backArrow.set_destRect(new Rect(bacArrowRect.get_left(),
                        bacArrowRect.get_right(),
                        -_G.getHeight() + bacArrowRect2.get_top() + (_G.getHeight() - bacArrowRect.get_top()) - 1,
                        bacArrowRect2.get_top() - 1));

                backArrow.set_destRect(new Rect(bacArrowRect.get_left(),
                        bacArrowRect.get_right(),
                        (bacArrowRect2.get_top() - _G.getHeight() + (BGspeed * deltaTime)),
                        (bacArrowRect2.get_top() + (BGspeed * deltaTime))));

                changePriority();
            }
            else if(getPriority() == 0) {
                backArrow.set_destRect(new Rect(bacArrowRect.get_left(),
                        bacArrowRect.get_right(),
                        (bacArrowRect.get_top() + (BGspeed * deltaTime)),
                        (bacArrowRect.get_bottom() + (BGspeed * deltaTime))));

            }
            else {
                backArrow.set_destRect(new Rect(bacArrowRect.get_left(),
                        bacArrowRect.get_right(),
                        (bacArrowRect2.get_top() - _G.getHeight() + (BGspeed * deltaTime)),
                        (bacArrowRect2.get_top() + (BGspeed * deltaTime))));
            }



            if(bacArrowRect2.get_top() >= _G.getHeight()) {
                backArrow2.set_destRect(new Rect(bacArrowRect2.get_left(),
                        bacArrowRect2.get_right(),
                        -_G.getHeight()+bacArrowRect.get_top()+(_G.getHeight()-bacArrowRect2.get_top())-1,
                        bacArrowRect.get_top()-1));

                backArrow2.set_destRect(new Rect(bacArrowRect2.get_left(),
                        bacArrowRect2.get_right(),
                        (bacArrowRect.get_top() - _G.getHeight() + (BGspeed * deltaTime)),
                        (bacArrowRect.get_top() + (BGspeed * deltaTime))));

                changePriority();
            }
            else if(getPriority() == 1) {
                backArrow2.set_destRect(new Rect(bacArrowRect2.get_left(),
                        bacArrowRect2.get_right(),
                        bacArrowRect2.get_top() +  (BGspeed * deltaTime),
                        bacArrowRect2.get_bottom() + (BGspeed * deltaTime)));
            }
            else {
                backArrow2.set_destRect(new Rect(bacArrowRect2.get_left(),
                        bacArrowRect2.get_right(),
                        (bacArrowRect.get_top() - _G.getHeight() + (BGspeed * deltaTime)),
                        (bacArrowRect.get_top() + (BGspeed * deltaTime))));
            }
        }
    }

    public String[] getBGcolors(){
        return BGcolors;
    }

    public void init(Game game){
       _game = game;
       _rM = new ResourceManager(_game);

        _G = _game.getGraphics();

        BehindBars bars = new BehindBars();

       loadImages();

       createSprites();

       _statesManager = new StatesManager(_game);

       State playState = new playState(_statesManager, _rM, bars);
       _statesManager.addState(playState, "playState");

        State instrState = new instructionsState(_statesManager, _rM, bars);
        _statesManager.addState(instrState, "instrState");

       State mainMenuState = new mainMenuState(_statesManager, _rM, bars);
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
        _G.clear(0xFF000000);
        _statesManager.getActualState().render();
        return true;
    }


}
