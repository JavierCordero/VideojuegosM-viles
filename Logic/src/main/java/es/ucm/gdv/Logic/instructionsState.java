package es.ucm.gdv.Logic;

import java.util.List;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Input;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.ResourceManager;
import es.ucm.gdv.engine.Sprite;
import es.ucm.gdv.engine.State;
import es.ucm.gdv.engine.StatesManager;

public class instructionsState extends State {

    Game _game;
    ResourceManager _rM;
    Graphics _G;
    //int colorMatch;
    //String[] BGcolors = {"greenBG", "cyanBG", "blueBG", "darkBlueBG", "purpleBG", "greyBG", "orangeBG", "redBG", "browBG"};

    StatesManager _statesManager;

    //Valores dados en el enunciado
    int tapToPlayY = 1464;
    int BGspeed = 384;
    int howToPlayY = 290;
    int instructionsY = 768;

    int prioridad = 0;

    Logic.BehindBars _Bar;

    public instructionsState(StatesManager statesManager, ResourceManager resourceManager,  Logic.BehindBars Bar){
        _statesManager = statesManager;
        _rM = resourceManager;
        _Bar = Bar;
    }

    @Override
    public void init(Game game) {
        _game = game;
        _G = _game.getGraphics();
        //colorMatch = (int) (Math.random() * BGcolors.length-1) + 1;
        //colorMatch = 0; //Color verde del array de colores
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> l = _game.getInput().getTouchEvents();
        for(int i = 0; i < l.size(); i++){
            Input.TouchEvent event = l.get(i);
            if(event.getEvent() == Input.EventType.TOUCH)
                _statesManager.chState("playState");
        }

        Graphics G = _game.getGraphics();

        _Bar.draw(deltaTime);
    }

    @Override
    public Boolean render() {
        //_rM.getSprite(BGcolors[colorMatch]).draw(G, new Rect(0,1080,0,1920));
        _rM.getSprite("greenBG").draw(_G, new Rect(0,1080,0,1920));

        Sprite backArrow = _rM.getSprite("BGArrow1");
        backArrow.draw(_G, backArrow.get_destRect());

        Sprite backArrow2 = _rM.getSprite("BGArrow2");
        backArrow2.draw(_G, backArrow2.get_destRect());

        Sprite howToPlay = _rM.getSprite("howToPlay");

        howToPlay.draw(_G, new Rect((_G.getWidth()/2)-howToPlay.getSpriteWidth()/2,
                (_G.getWidth()/2)+howToPlay.getSpriteWidth()/2
                ,howToPlayY,
                howToPlayY + howToPlay.getSpriteHeight()));

        Sprite instructions = _rM.getSprite("instructions");

        instructions.draw(_G, new Rect((_G.getWidth()/2)-instructions.getSpriteWidth()/2,
                (_G.getWidth()/2)+instructions.getSpriteWidth()/2
                ,instructionsY,
                instructionsY + instructions.getSpriteHeight()));

        Sprite tapToPlay = _rM.getSprite("ToPlay");

        tapToPlay.draw(_G, new Rect((_G.getWidth()/2)-tapToPlay.getSpriteWidth()/2,
                (_G.getWidth()/2)+tapToPlay.getSpriteWidth()/2
                ,tapToPlayY,
                tapToPlayY + tapToPlay.getSpriteHeight()));

        return true;
    }
}
