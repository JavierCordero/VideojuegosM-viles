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

/*task copyPNGs(type: Copy){
    description = "Copy image into assset folder..."
        from(rootDir){
            include "sprites/**"
        }

        into "src/main/assets"
}*/

public class mainMenuState extends State {

    Game _game;
    ResourceManager _rM;
    Graphics _G;
    StatesManager _statesManager;

    //Valores dados en el enunciado
    int tapToPlayY = 950;
    int logoY = 356;
    int prioridad = 0;

    Logic.BehindBars _Bar;

    public mainMenuState(StatesManager statesManager, ResourceManager resourceManager,  Logic.BehindBars Bar){
        _statesManager = statesManager;
        _rM = resourceManager;
        _Bar = Bar;
    }

    @Override
    public void init(Game game) {
        _game = game;
        _G = _game.getGraphics();
        //colorMatch = (int) (Math.random() * BGcolors.length-1) + 1;
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> l = _game.getInput().getTouchEvents();
        for(int i = 0; i < l.size(); i++){
            Input.TouchEvent event = l.get(i);
            if(event.getEvent() == Input.EventType.TOUCH)
                _statesManager.chState("instrState");
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

        Sprite logo = _rM.getSprite("logo");

        logo.draw(_G, new Rect((_G.getWidth()/2)-logo.getSpriteWidth()/2,
                (_G.getWidth()/2)+logo.getSpriteWidth()/2
                ,logoY,
                logoY + logo.getSpriteHeight()));

        Sprite tapToPlay = _rM.getSprite("ToPlay");

        tapToPlay.draw(_G, new Rect((_G.getWidth()/2)-tapToPlay.getSpriteWidth()/2,
                (_G.getWidth()/2)+tapToPlay.getSpriteWidth()/2
                    ,tapToPlayY,
                tapToPlayY + tapToPlay.getSpriteHeight()));

        return true;
    }
}
