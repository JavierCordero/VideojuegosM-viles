package es.ucm.gdv.Logic;

import java.util.List;

import es.ucm.gdv.engine.Button;
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


    StatesManager _statesManager;

    //Valores dados en el enunciado
    int tapToPlayY = 1464;
    int BGspeed = 384;
    int howToPlayY = 290;
    int instructionsY = 768;

    int prioridad = 0;

    Arrows _arrows;
    Logic.BehindColor _bColor;

    Button _quitButton;

    flashEffect _flashEffect;

    Sin _sin;

    public instructionsState(StatesManager statesManager, ResourceManager resourceManager,
                             Arrows arrow, Logic.BehindColor bColor){
        _statesManager = statesManager;
        _rM = resourceManager;
        _arrows = arrow;
        _bColor = bColor;
    }

    @Override
    public void handleEvent(Input.TouchEvent event){
        if(event.getEvent() == Input.EventType.TOUCH)
            if(_quitButton.buttonPressed(event.get_x(), event.get_y())) {
                _statesManager.enqueueState("playState");
            }
            else
                _statesManager.enqueueState("playState");
    }


    @Override
    public void init(Game game) {
        _game = game;
        _G = _game.getGraphics();
        _quitButton = new Button(_rM.getSprite("exit"), new Rect(970,1070,200,300), _G);

        _flashEffect = new flashEffect();
        _flashEffect.init(_rM, _G);

        _sin = new Sin();
    }

    @Override
    public void update(float deltaTime) {
        _bColor.setCurrentColor(0);

        Graphics G = _game.getGraphics();

        _arrows.draw(deltaTime);

        _flashEffect.changeAlpha();
    }

    @Override
    public Boolean render() {
        Sprite backArrow = _rM.getSprite("BGArrow1");
        Rect bacArrowRect = backArrow.get_destRect();
        _rM.getSprite(_bColor.getBGcolors()[_bColor.currentColor]).draw(_G, new Rect(bacArrowRect.get_left(),
                bacArrowRect.get_right(),
                0,
                _G.getHeight()));
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
        tapToPlay.modifyAlpha(_sin.updateSin());
        tapToPlay.draw(_G, new Rect((_G.getWidth()/2)-tapToPlay.getSpriteWidth()/2,
                (_G.getWidth()/2)+tapToPlay.getSpriteWidth()/2
                ,tapToPlayY,
                tapToPlayY + tapToPlay.getSpriteHeight()));

        _quitButton.drawButton();

        _flashEffect.draw();

        return true;
    }
}
