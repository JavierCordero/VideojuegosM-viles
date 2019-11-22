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
import es.ucm.gdv.engine.Button;
import sun.nio.cs.KOI8_R;

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

    Arrows _arrows;
    Logic.BehindColor _bColor;

    Button _soundButton;
    Button _instructionButton;

    flashEffect _flashEffect;

    Sin _sin;

    public mainMenuState(StatesManager statesManager, ResourceManager resourceManager,
                         Arrows arrow, Logic.BehindColor bColor){
        _statesManager = statesManager;
        _rM = resourceManager;
        _arrows = arrow;
        _bColor = bColor;
    }

    @Override
    public void handleEvent(Input.TouchEvent event){
            if(event.getEvent() == Input.EventType.TOUCH)
                if(_soundButton.buttonPressed(event.get_x(), event.get_y())) {
                    if (!_soundButton.getPressed())
                        _soundButton.changeButtonSprite(_rM.getSprite("mute"));
                    else
                        _soundButton.changeButtonSprite(_rM.getSprite("sound"));
                }

            else if(_instructionButton.buttonPressed(event.get_x(), event.get_y())) {
                    _statesManager.enqueueState("instrState");
                }
            else
                _statesManager.enqueueState("instrState");
    }

    @Override
    public void init(Game game) {
        _game = game;
        _G = _game.getGraphics();

        _soundButton = new Button(_rM.getSprite("sound"), new Rect(20,120,200,300), _G);
        _instructionButton = new Button(_rM.getSprite("interrogation"), new Rect(970,1070,200,300), _G);

        _flashEffect = new flashEffect();
        _flashEffect.init(_rM, _G);

        _sin = new Sin();

    }

    @Override
    public void update(float deltaTime) {

        _bColor.setCurrentColor(0); // color verde
        _arrows.draw(deltaTime);
        _flashEffect.changeAlpha();
    }

    @Override
    public Boolean render() {
         Sprite backArrow = _rM.getSprite("BGArrow1");
         Rect bacArrowRect = backArrow.get_destRect();
        _rM.getSprite(_bColor.getBGcolors()[_bColor.currentColor]).draw(_G, new Rect(bacArrowRect.get_left()+1,
                bacArrowRect.get_right()-1,
                0,
                _G.getHeight()));


        backArrow.draw(_G, backArrow.get_destRect());

        Sprite backArrow2 = _rM.getSprite("BGArrow2");

        backArrow2.draw(_G, backArrow2.get_destRect());

        Sprite logo = _rM.getSprite("logo");

        logo.draw(_G, new Rect((_G.getWidth()/2)-logo.getSpriteWidth()/2,
                (_G.getWidth()/2)+logo.getSpriteWidth()/2
                ,logoY,
                logoY + logo.getSpriteHeight()));

        Sprite tapToPlay = _rM.getSprite("ToPlay");

        tapToPlay.modifyAlpha(_sin.updateSin());

        tapToPlay.draw(_G, new Rect((_G.getWidth()/2)-tapToPlay.getSpriteWidth()/2,
                (_G.getWidth()/2)+tapToPlay.getSpriteWidth()/2
                    ,tapToPlayY,
                tapToPlayY + tapToPlay.getSpriteHeight()));

        _soundButton.drawButton();
        _instructionButton.drawButton();

        _flashEffect.draw();

        return true;
    }
}
