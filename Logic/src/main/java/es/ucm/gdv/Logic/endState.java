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

    int numbersHeight = 800;
    int numbersSeparation = 70;
    int _score;
    Sprite numbers [] = new Sprite [10];

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

        for( int i = 0; i < 10; i++){
            numbers[i] =_rM.getSprite("number" + i);
        }
    }

    @Override
    public void update(float deltaTime) {
        _bColor.setCurrentColor(2); // color verde
        List<Input.TouchEvent> l = _game.getInput().getTouchEvents();
        for(int i = 0; i < l.size(); i++){
            Input.TouchEvent event = l.get(i);
            if(event.getEvent() == Input.EventType.TOUCH)
                _statesManager.chState("playState");
        }

        _Bar.draw(deltaTime);
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

        return true;
    }

    void drawNumber(int n, int separation){
        numbers[n].draw(_G, new Rect(_G.getWidth() / 2 + separation * numbersSeparation,
                _G.getWidth() / 2 + numbers[n].getSpriteWidth() + separation * numbersSeparation,
                numbersHeight, numbersHeight + numbers[n].getSpriteHeight()));
    }
}
