package es.ucm.gdv.Logic;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Input;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.ResourceManager;
import es.ucm.gdv.engine.Sprite;
import es.ucm.gdv.engine.State;
import es.ucm.gdv.engine.StatesManager;

public class playState extends State {

    class ball{
        boolean active;
        Sprite _mySprite;
        String _myColor;

        public void setColor (String c){_myColor = c;}

        public ball(Sprite sprite){
            _mySprite = sprite;
        }

        public void changeBallSprite(Sprite newSprite){
            _mySprite = newSprite;
        }

        public Sprite getBallSprite(){
            return _mySprite;
        }

        public String get_myColor(){
            return _myColor;
        }
    }

    Game _game;
    ResourceManager _rM;

    StatesManager _statesManager;

    String actualPlayer = "whitePlayer";
    String actualBall = "whiteBall";

    //Valores dados en el enunciado
    int _originalBallSpeed = 430;
    int _ballsSpeed;
    int ballSeparation = 395;
    int incrBallSpeed = 90;
    int playerDistanceToTop = 1200;
    int ballSize = 128;

    int _ballsTaken;

    int numBalls = 5;


    int numbersHeight = 400;
    int numbersSeparation = 70;

    ball [] usedBalls = new ball[numBalls]; //= new LinkedList<>();

    ball lastBallCreated;

    Graphics _G;

    Logic.BehindBars _Bar;
    Logic.BehindColor _bColor;

    int _myColor;

    int numSprites = 0;

    Sprite numbers [] = new Sprite [10];

    public playState(StatesManager statesManager, ResourceManager resourceManager,
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

        _ballsTaken = 0;
        _ballsSpeed = _originalBallSpeed;

        for(int i = 0; i < numBalls; i++) {

            ball b = new ball(_rM.getSprite("whiteBall"));
            b = newRandomBall(b);
            b.active = false;
            usedBalls[i] = b;
        }

        for( int i = 0; i < 10; i++){
            numbers[i] =_rM.getSprite("number" + i);
        }

        usedBalls[0].active = true;
        lastBallCreated = usedBalls[0];

        _myColor = (int) (Math.random() * _bColor.getBGcolors().length-1) + 1;

        Sprite white = _rM.getSprite("whitePlayer");
        Sprite black = _rM.getSprite("blackPlayer");

        white.set_destRect(new Rect((_G.getWidth()/2)-white.getSpriteWidth()/2,
                (_G.getWidth()/2)+white.getSpriteWidth()/2,
                playerDistanceToTop-white.getSpriteHeight()/2,
                playerDistanceToTop+white.getSpriteHeight()/2));

        black.set_destRect(new Rect((_G.getWidth()/2)-black.getSpriteWidth()/2,
                (_G.getWidth()/2)+black.getSpriteWidth()/2,
                playerDistanceToTop-black.getSpriteHeight()/2,
                playerDistanceToTop+black.getSpriteHeight()/2));

    }

    @Override
    public void update(float deltaTime) {

        _bColor.setCurrentColor(_myColor);

        List<Input.TouchEvent> l = _game.getInput().getTouchEvents();
        for(int i = 0; i < l.size(); i++){
            Input.TouchEvent event = l.get(i);
            if(event.getEvent() == Input.EventType.TOUCH)
                cambiaPlayer();
        }
        _Bar.draw(deltaTime);

        for(int i = 0; i < usedBalls.length; i++) {

            if(usedBalls[i].active){
                Sprite ball = usedBalls[i].getBallSprite();
                Rect ballRect = ball.get_destRect();

                int coso = (int) (_ballsSpeed * deltaTime);

                ball.set_destRect(new Rect(ballRect.get_left(), ballRect.get_right(),
                    ballRect.get_top() + (_ballsSpeed * deltaTime),
                        ballRect.get_bottom() + (_ballsSpeed * deltaTime)));

                if(ball.get_destRect().get_bottom() >= _rM.getSprite(actualPlayer).get_destRect().get_top() &&
                        actualPlayer != usedBalls[i].get_myColor())
                {
                    //el juego ha acabado has perdido
                    endState s = (endState)_statesManager.get_state_by_name("endState");
                    s.setScore(_ballsTaken);
                    _statesManager.chState("endState");
                }

                else if(ball.get_destRect().get_bottom() >= _rM.getSprite(actualPlayer).get_destRect().get_top()
                && ball.get_destRect().get_bottom() < _rM.getSprite(actualPlayer).get_destRect().get_bottom()
                && actualPlayer == usedBalls[i].get_myColor()){
                    usedBalls[i].active = false;
                    _ballsTaken++;

                    if(_ballsTaken % 11 == 0)
                        _ballsSpeed += incrBallSpeed;
                }

                if(lastBallCreated.getBallSprite().get_destRect().get_top() >= ballSeparation){
                    ball b = firstUnusedBall();
                    newRandomBall(b).getBallSprite().set_destRect(new Rect((_G.getWidth() / 2) - ballSize / 2,
                            (_G.getWidth() / 2) + ballSize / 2,
                            -ballSize,
                            0 ));
                    b.active = true;
                    lastBallCreated = b;
                }
            }
        }
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

        for(int i = 0; i < numBalls; i++) {
            if(usedBalls[i].active)
                usedBalls[i].getBallSprite().draw(_G, usedBalls[i].getBallSprite().get_destRect());
        }

        Sprite player = _rM.getSprite(actualPlayer);

        player.draw(_G,player.get_destRect());

        int residuo;
        int centenas = (_ballsTaken/100);
        residuo = _ballsTaken%100;
        int decenas = (residuo/10);
        residuo = residuo%10;
        int unidades = (residuo/1);

        drawNumber(unidades, decenas, centenas);


        return true;
    }

    void drawNumber(int unidades, int decenas, int centenas){
        Rect unidadesRect = new Rect(_G.getWidth()-numbers[unidades].getSpriteWidth()-20,
                                    _G.getWidth()-20,
                                    numbersHeight,
                                    numbersHeight+numbers[unidades].getSpriteHeight());

        Rect decenasRect = new Rect(unidadesRect.get_left()-numbers[decenas].getSpriteWidth(),
                                    unidadesRect.get_left()-1,
                                    numbersHeight,
                                    numbersHeight+numbers[decenas].getSpriteHeight());

        Rect centenasRect = new Rect(decenasRect.get_left()-numbers[centenas].getSpriteWidth(),
                                decenasRect.get_left()-1,
                             numbersHeight,
                            numbersHeight+numbers[centenas].getSpriteHeight());

        numbers[unidades].draw(_G, unidadesRect);
        if(_ballsTaken > 9)numbers[decenas].draw(_G, decenasRect);
        if(_ballsTaken > 99)numbers[centenas].draw(_G, centenasRect);
    }

    void cambiaPlayer(){
        if(actualPlayer == "whitePlayer") actualPlayer = "blackPlayer";
        else actualPlayer = "whitePlayer";
    }

    void cambiaBall(){
        if(actualBall == "whiteBall") actualBall = "blackBall";
        else actualBall = "whiteBall";
    }

    ball firstUnusedBall(){
        int i;
        for(i = 0; i < usedBalls.length - 1; i++){
            if(!usedBalls[i].active)
                break;
        }
        return usedBalls[i];
    }

    ball newRandomBall(ball b){
        int rnd = (int) (Math.random() * 100) + 1; // end entre 0 y 100

        if(rnd  > 70)
            cambiaBall();

        Image balls = _rM.getImage("balls");


        if(actualBall == "blackBall") {

             _rM.createSpriteFromImage("balls",
                    new Rect(0, 1280 / 10, 0, balls.getHeight() / 2), "blackBall" + numSprites, 255);
            Sprite s = _rM.getSprite("blackBall" + numSprites);
            b.changeBallSprite(s);
            b.setColor("whitePlayer");
        }
        else {
            _rM.createSpriteFromImage("balls",
                    new Rect(0, 1280 / 10, balls.getHeight() / 2, balls.getHeight()), "whiteBall" + numSprites, 255);
            Sprite s = _rM.getSprite("whiteBall" + numSprites);
            b.changeBallSprite(s);
            b.setColor("blackPlayer");
        }

        b.getBallSprite().set_destRect(new Rect((_G.getWidth() / 2) - ballSize / 2,
                (_G.getWidth() / 2) + ballSize / 2,
                -ballSize,
                0 ));
        return b;
    }
}
