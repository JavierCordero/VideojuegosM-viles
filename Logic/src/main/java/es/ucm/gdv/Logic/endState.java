package es.ucm.gdv.Logic;

import java.util.List;

import es.ucm.gdv.engine.Button;
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
   Arrows _arrows;
    Logic.BehindColor _bColor;
    Game _game;
    Graphics _G;

    int numbersHeight = 800;
    int numbersSeparation = 70;
    int _score;
    Sprite numbers [] = new Sprite [10];

    Button _soundButton;
    Button _instructionButton;

    flashEffect _flashEffect;

    Sin _sin;

    public void setScore(int score){
        _score = score;
    }

    public endState(StatesManager statesManager, ResourceManager resourceManager,
                    Arrows arrow, Logic.BehindColor bColor){
        _statesManager = statesManager;
        _rM = resourceManager;
        _arrows = arrow;
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

        for( int i = 0; i < 10; i++){
            numbers[i] =_rM.getSprite("number" + i);
        }

        _soundButton = new Button(_rM.getSprite("sound"), new Rect(20,120,200,300), _G);
        _instructionButton = new Button(_rM.getSprite("interrogation"), new Rect(970,1070,200,300), _G);

        _flashEffect = new flashEffect();
        _flashEffect.init(_rM, _G);

        _sin = new Sin();
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
                    _statesManager.enqueueState("playState");
    }

    @Override
    public void update(float deltaTime) {
        _bColor.setCurrentColor(2); // color verde
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

        Sprite gOver = _rM.getSprite("gameOver");
        gOver.draw(_G, gOver.get_destRect());

        Sprite pAgain = _rM.getSprite("playAgain");
        pAgain.modifyAlpha(_sin.updateSin());
        pAgain.draw(_G, pAgain.get_destRect());


        //Score
        int n =(int)(_score) / 100;
        drawNumber(n, -2);

        n = (_score) % 100 / 10;
        drawNumber(n, -1);

        n = (_score) % 100 % 10;
        if(n == 10) n = 0;
        drawNumber(n, 0);

        //Points
        Sprite letter = _rM.getSprite("letrap");
        letter.draw(_G, new Rect(_G.getWidth() / 2 - 3 * numbersSeparation,
                _G.getWidth() / 2 + numbers[n].getSpriteWidth() - 3 * numbersSeparation,
                numbersHeight + 200,numbersHeight + 300));

        letter = _rM.getSprite("letrao");
        letter.draw(_G, new Rect(_G.getWidth() / 2 -2 * numbersSeparation,
                _G.getWidth() / 2 + numbers[n].getSpriteWidth() -2 * numbersSeparation,
                numbersHeight + 200,numbersHeight + 300));

        letter = _rM.getSprite("letrai");
        letter.draw(_G, new Rect(_G.getWidth() / 2 -1 * numbersSeparation,
                _G.getWidth() / 2 + numbers[n].getSpriteWidth() - 1* numbersSeparation,
                numbersHeight + 200,numbersHeight + 300));

        letter = _rM.getSprite("letran");
        letter.draw(_G, new Rect(_G.getWidth() / 2 + 0 * numbersSeparation,
                _G.getWidth() / 2 + numbers[n].getSpriteWidth() + 0 * numbersSeparation,
                numbersHeight + 200,numbersHeight + 300));

        letter = _rM.getSprite("letrat");
        letter.draw(_G, new Rect(_G.getWidth() / 2 + 1 * numbersSeparation,
                _G.getWidth() / 2 + numbers[n].getSpriteWidth() + 1 * numbersSeparation,
                numbersHeight + 200,numbersHeight + 300));

        letter = _rM.getSprite("letras");
        letter.draw(_G, new Rect(_G.getWidth() / 2 + 2 * numbersSeparation,
                _G.getWidth() / 2 + numbers[n].getSpriteWidth() + 2 * numbersSeparation,
                numbersHeight + 200,numbersHeight + 300));

        _soundButton.drawButton();
        _instructionButton.drawButton();

        _flashEffect.draw();

        return true;
    }

    void drawNumber(int n, int separation){
        numbers[n].draw(_G, new Rect(_G.getWidth() / 2 + separation * numbersSeparation,
                _G.getWidth() / 2 + numbers[n].getSpriteWidth() + separation * numbersSeparation,
                numbersHeight, numbersHeight + numbers[n].getSpriteHeight()));
    }
}
