package es.ucm.gdv.Logic;

import java.util.List;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Input;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.ResourceManager;
import es.ucm.gdv.engine.Sprite;
import es.ucm.gdv.engine.State;
import es.ucm.gdv.engine.StatesManager;

public class endState extends State {

    ResourceManager _rM;
    StatesManager _statesManager;
    Logic.BehindBars _Bar;
    Logic.BehindColor _bColor;
    Game _game;
    Graphics _G;

    int _score;

    public void setScore(int score){
        _score = score;
    }

    public endState(StatesManager statesManager, ResourceManager resourceManager,
                    Logic.BehindBars Bar, Logic.BehindColor bColor){
        _statesManager = statesManager;
        _rM = resourceManager;
        _Bar = Bar;
        _bColor = bColor;
    }

    @Override
    public void init(Game game) {
        _game = game;
        _G = _game.getGraphics();

        Sprite gOver =  _rM.getSprite("gameOver");
        gOver.set_destRect(new Rect((_G.getWidth()/2)-gOver.getSpriteWidth()/2,
                (_G.getWidth()/2)+gOver.getSpriteWidth()/2,
                364,
                364 + gOver.getSpriteHeight()));

        Sprite pAgain = _rM.getSprite("playAgain");
        pAgain.set_destRect(new Rect((_G.getWidth()/2)-pAgain.getSpriteWidth()/2,
                (_G.getWidth()/2)+pAgain.getSpriteWidth()/2,
                1396,
                1396 + pAgain.getSpriteHeight()));
    }

    @Override
    public void update(float deltaTime) {
        _bColor.setCurrentColor(2); // color verde
        List<Input.TouchEvent> l = _game.getInput().getTouchEvents();
        for(int i = 0; i < l.size(); i++){
            Input.TouchEvent event = l.get(i);
            if(event.getEvent() == Input.EventType.TOUCH)
                _statesManager.chState("mainMenuState");
        }

        _Bar.draw(deltaTime);
    }

    @Override
    public Boolean render() {
        _rM.getSprite(_bColor.getBGcolors()[_bColor.currentColor]).draw(_G, new Rect(0,1080,0,1920));

        Sprite backArrow = _rM.getSprite("BGArrow1");
        backArrow.draw(_G, backArrow.get_destRect());

        Sprite backArrow2 = _rM.getSprite("BGArrow2");
        backArrow2.draw(_G, backArrow2.get_destRect());

        Sprite gOver = _rM.getSprite("gameOver");
        gOver.draw(_G, gOver.get_destRect());

        Sprite pAgain = _rM.getSprite("playAgain");
        pAgain.draw(_G, pAgain.get_destRect());

        return true;
    }
}
