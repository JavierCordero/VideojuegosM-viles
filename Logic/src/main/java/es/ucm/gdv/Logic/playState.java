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

    Game _game;
    ResourceManager _rM;

    StatesManager _statesManager;

    String actualPlayer = "whitePlayer";
    String actualBall = "whiteBall";

    //Valores dados en el enunciado
    int BGspeed = 384;
    int ballsSpeed = 430;
    int ballSeparation = 395;
    int incrBallSpeed = 90;
    int playerDistanceToTop = 1200;
    int ballSize = 128;

    int ballsTaken = 1;

    //Queue<Sprite> unusedBlackBalls = new LinkedList<>();
    //Queue<Sprite> unusedWhiteBalls = new LinkedList<>();
    //Queue<Sprite> usedBalls= new LinkedList<>();

    public playState(StatesManager statesManager, ResourceManager resourceManager){
        _statesManager = statesManager;
        _rM = resourceManager;
    }

    @Override
    public void init(Game game) {
        _game = game;

        /*Image balls = _rM.getImage("balls");

        for(int i = 0; i < 20; i++) {
            _rM.createSpriteFromImage("balls",
                    new Rect(0, 1280 / 10, balls.getHeight() / 2, balls.getHeight()),
                    "blackBall" + i);
            unusedBlackBalls.add(_rM.getSprite("blackBall" + i));
        }

        for(int i = 0; i < 20; i++) {
            _rM.createSpriteFromImage("balls",
                    new Rect(0, 1280 / 10, balls.getHeight() / 2, balls.getHeight()),
                    "whiteBall" + i);
            unusedWhiteBalls.add(_rM.getSprite("whiteBall" + i));
        }

        for(int i = 0; i < 5; i++) {

            //Random para ver que color de bola sacamos ahora
            int rnd = (int) (Math.random() * 100) + 1; // end entre 0 y 100

            if(rnd  > 70)
                cambiaBall();

            Sprite ball;

            if(actualBall == "blackBall") {
                ball = unusedBlackBalls.remove();
                usedBalls.add(ball);
            }
            else {
                ball = unusedWhiteBalls.remove();
                usedBalls.add(ball);
            }
            ball.set_destRect(new Rect((game.getGraphics().getWidth() / 2) - ballSize / 2,
                    (game.getGraphics().getWidth() / 2) + ballSize / 2,
                    -(i * ballSeparation)-ballSize,
                    -(i * ballSeparation)));
        }*/
    }

    @Override
    public void update(float deltaTime) {

        List<Input.TouchEvent> l = _game.getInput().getTouchEvents();
        for(int i = 0; i < l.size(); i++){
            Input.TouchEvent event = l.get(i);
            if(event.getEvent() == Input.EventType.TOUCH)
                cambiaPlayer();
        }

        if(ballsTaken % 11 == 0)
            ballsSpeed += incrBallSpeed;

        Graphics G = _game.getGraphics();

        Sprite backArrow = _rM.getSprite("BGArrow1");
        Rect bacArrowRect = backArrow.get_destRect();

        if(bacArrowRect.get_top() > G.getHeight())
            backArrow.set_destRect(new Rect(bacArrowRect.get_left(),
                    bacArrowRect.get_right(),
                    -G.getHeight(),
                    0));
        else {
            backArrow.set_destRect(new Rect(bacArrowRect.get_left(),
                    bacArrowRect.get_right(),
                    bacArrowRect.get_top() + (int) (BGspeed * deltaTime),
                    bacArrowRect.get_bottom() + (int) (BGspeed * deltaTime)));
        }


        Sprite backArrow2 = _rM.getSprite("BGArrow2");
        Rect bacArrowRect2 = backArrow2.get_destRect();

        if(bacArrowRect2.get_top() > G.getHeight())
            backArrow2.set_destRect(new Rect(bacArrowRect2.get_left(),
                    bacArrowRect2.get_right(),
                    - G.getHeight(),
                    0));
        else {
            backArrow2.set_destRect(new Rect(bacArrowRect2.get_left(),
                    bacArrowRect2.get_right(),
                    bacArrowRect2.get_top() + (int) (BGspeed * deltaTime),
                    bacArrowRect2.get_bottom() + (int) (BGspeed * deltaTime)));
        }


        /*for(int i = 0; i < usedBalls.size(); i++) {

            Sprite ball = usedBalls.remove();
            Rect ballRect = ball.get_destRect();

            ball.set_destRect(new Rect(ballRect.get_left(), ballRect.get_right(),
                    ballRect.get_top() + (int) (ballsSpeed * deltaTime), ballRect.get_bottom() + (int) (ballsSpeed * deltaTime)));

            if(ball.get_destRect().get_top() >= G.getHeight() - 1)
            {
                      //Random para ver que color de bola sacamos ahora
                int rnd = (int) (Math.random() * 100) + 1; // end entre 0 y 100

                if(rnd  > 70)
                    cambiaBall();

                Sprite nBall;

                if(actualBall == "blackBall") {
                    nBall = unusedBlackBalls.remove();
                    usedBalls.add(nBall);
                    unusedWhiteBalls.add(ball);
                }
                else {
                    nBall = unusedWhiteBalls.remove();
                    usedBalls.add(nBall);
                    unusedBlackBalls.add(ball);
                }

                nBall.set_destRect(new Rect((G.getWidth() / 2) - ballSize / 2,
                        (G.getWidth() / 2) + ballSize / 2,
                        -ballSize,
                        0 ));
            }

            else usedBalls.add(ball);
        }*/
    }
    @Override
    public Boolean render() {

        Graphics G = _game.getGraphics();
        G.clear(0xFF000000);
        //_rM.getSprite(BGcolors[colorMatch]).draw(G, new Rect(0,1080,0,1920));
        _rM.getSprite("redBG").draw(G, new Rect(0,1080,0,1920));

        Sprite backArrow = _rM.getSprite("BGArrow1");
        backArrow.draw(G, backArrow.get_destRect());

        Sprite backArrow2 = _rM.getSprite("BGArrow2");
        backArrow2.draw(G, backArrow2.get_destRect());

        /*for(int i = 0; i < 5; i++) {
            Sprite ball = usedBalls.remove();

            ball.draw(G, ball.get_destRect());

            usedBalls.add(ball);
        }*/

        Sprite player = _rM.getSprite(actualPlayer);

        player.draw(G,
                new Rect((G.getWidth()/2)-player.getSpriteWidth()/2,
                        (G.getWidth()/2)+player.getSpriteWidth()/2,
                        playerDistanceToTop-player.getSpriteHeight()/2,
                        playerDistanceToTop+player.getSpriteHeight()/2));
        // _resourceManager.getSprite("whitePlayer").draw(_game.getGraphics(),   new Rect(540-264,540+264,1200,1397));

        return true;
    }

    void cambiaPlayer(){
        if(actualPlayer == "whitePlayer") actualPlayer = "blackPlayer";
        else actualPlayer = "whitePlayer";
    }

    void cambiaBall(){
        if(actualBall == "whiteBall") actualBall = "blackBall";
        else actualBall = "whiteBall";
    }
}
