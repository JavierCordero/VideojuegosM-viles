package es.ucm.gdv.Logic;

import java.util.Random;

import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.ResourceManager;
import es.ucm.gdv.engine.Sprite;
import es.ucm.gdv.engine.StatesManager;

class ball{
    boolean active = false;
    Sprite _mySprite;
    String _myColor;

    public void setColor (String c){_myColor = c;}

    public ball(Sprite sprite){
        _mySprite = sprite;
    }

    public Sprite getBallSprite(){
        return _mySprite;
    }

    public String get_myColor(){
        return _myColor;
    }
}

public class ballManager {
    ResourceManager _rM;
    Graphics _G;
    particleSystem _p;
    StatesManager _sM;


    int _originalBallSpeed = 430;
    int _ballsSpeed;
    int ballSeparation = 395;
    int incrBallSpeed = 90;
    int ballSize = 128;
    String actualPlayer = "whitePlayer";
    Boolean actual = true;

    int _ballsTaken;
    int numBalls = 5;
    ball[] WhiteBalls = new ball[numBalls];
    ball[] BlackBalls = new ball[numBalls];
    ball lastBallCreated;

    public ballManager(ResourceManager resourceManager, Graphics graphics, particleSystem p,  StatesManager sM){
        _rM = resourceManager;
        _G = graphics;
        _ballsTaken = 0;
        _ballsSpeed = _originalBallSpeed;
        _p = p;
        _sM = sM;

        Image balls = _rM.getImage("balls");
        for(int i = 0; i < numBalls; i++) {
            _rM.createSpriteFromImage("balls",
                    new Rect(0, 1280 / 10, 0, balls.getHeight() / 2),
                    "whiteBall"+i, 255);

            _rM.createSpriteFromImage("balls",
                    new Rect(0, 1280 / 10, balls.getHeight() / 2, balls.getHeight()),
                    "blackBall"+i, 255);


            WhiteBalls[i] = new ball(_rM.getSprite("whiteBall"+i));
            BlackBalls[i] = new ball(_rM.getSprite("blackBall"+i));
            WhiteBalls[i].setColor("whitePlayer");
            BlackBalls[i].setColor("blackPlayer");

            WhiteBalls[i].getBallSprite().set_destRect(new Rect((_G.getWidth() / 2) - ballSize / 2,
                    (_G.getWidth() / 2) + ballSize / 2,
                    -ballSize,
                    0 ));

            BlackBalls[i].getBallSprite().set_destRect(new Rect((_G.getWidth() / 2) - ballSize / 2,
                    (_G.getWidth() / 2) + ballSize / 2,
                    -ballSize,
                    0 ));
        }
        int nBall = new Random().nextInt(2);
        ball b;
        if(nBall == 0){
            b = WhiteBalls[0];
            actual = true;
        }
        else{
            b = BlackBalls[0];
            actual = false;
        }
        b.active = true;
        lastBallCreated = b;
    }

   public void actualizaBola(float deltaTime) {
       for (int i = 0; i < numBalls; i++) {

           if (WhiteBalls[i].active) {
               Sprite ball = WhiteBalls[i].getBallSprite();
               Rect ballRect = ball.get_destRect();

               ball.set_destRect(new Rect(ballRect.get_left(), ballRect.get_right(),
                       ballRect.get_top() + (_ballsSpeed * deltaTime),
                       ballRect.get_bottom() + (_ballsSpeed * deltaTime)));

               if (ball.get_destRect().get_bottom() >= _rM.getSprite(actualPlayer).get_destRect().get_top() &&
                       actualPlayer != WhiteBalls[i].get_myColor()) {
                   //el juego ha acabado has perdido
                   endState s = (endState) _sM.get_state_by_name("endState");
                   s.setScore(_ballsTaken);
                   _sM.enqueueState("endState");
               } else if (ball.get_destRect().get_bottom() >= _rM.getSprite(actualPlayer).get_destRect().get_top()
                       && ball.get_destRect().get_bottom() < _rM.getSprite(actualPlayer).get_destRect().get_bottom()
                       && actualPlayer == WhiteBalls[i].get_myColor()) {
                   WhiteBalls[i].active = false;
                   _p.ActivaParticulas(WhiteBalls[i]);
                   _ballsTaken++;
                   if(_ballsTaken > 999) _ballsTaken = 0;

                   if (_ballsTaken != 0 && _ballsTaken % 11 == 0)
                       _ballsSpeed += incrBallSpeed;
               }
              createFromDistance();
           }

           if (BlackBalls[i].active) {
               Sprite ball = BlackBalls[i].getBallSprite();
               Rect ballRect = ball.get_destRect();

               ball.set_destRect(new Rect(ballRect.get_left(), ballRect.get_right(),
                       ballRect.get_top() + (_ballsSpeed * deltaTime),
                       ballRect.get_bottom() + (_ballsSpeed * deltaTime)));

               if (ball.get_destRect().get_bottom() >= _rM.getSprite(actualPlayer).get_destRect().get_top() &&
                       actualPlayer != BlackBalls[i].get_myColor()) {
                   //el juego ha acabado has perdido
                   endState s = (endState) _sM.get_state_by_name("endState");
                   s.setScore(_ballsTaken);
                   _sM.enqueueState("endState");
               } else if (ball.get_destRect().get_bottom() >= _rM.getSprite(actualPlayer).get_destRect().get_top()
                       && ball.get_destRect().get_bottom() < _rM.getSprite(actualPlayer).get_destRect().get_bottom()
                       && actualPlayer == BlackBalls[i].get_myColor()) {
                   BlackBalls[i].active = false;
                   _p.ActivaParticulas(BlackBalls[i]);
                   _ballsTaken++;
                   if(_ballsTaken > 999) _ballsTaken = 0;
                   if (_ballsTaken != 0 && _ballsTaken % 11 == 0)
                       _ballsSpeed += incrBallSpeed;
               }
               createFromDistance();
           }


       }
   }

   void createFromDistance(){
       if (lastBallCreated.getBallSprite().get_destRect().get_top() >= ballSeparation) {
           cambiaBola();
           ball b = firstUnusedBall(actual);
           b.getBallSprite().set_destRect(new Rect((_G.getWidth() / 2) - ballSize / 2,
                   (_G.getWidth() / 2) + ballSize / 2,
                   -ballSize,
                   0 ));
           b.active = true;
           lastBallCreated = b;
       }
   }

   ball firstUnusedBall(Boolean type){
       int i;
       for(i = 0; i < numBalls ; i++){
           if(type) {
               if (!WhiteBalls[i].active)
                   break;
           }
           else{
               if (!BlackBalls[i].active)
                   break;
           }
       }

       if(type) {
           return WhiteBalls[i];
       }
       else{
           return BlackBalls[i];
       }

   }

   void cambiaBola(){
       int rnd = (int) (Math.random() * 100) + 1; // end entre 0 y 100

       if(rnd  > 70){
           actual = !actual;
       }
   }

   public void renderBalls(){
        for(int i = 0; i < numBalls; i++){
            if(WhiteBalls[i].active) {
                WhiteBalls[i]._mySprite.draw(_G, WhiteBalls[i]._mySprite.get_destRect());
            }
            if(BlackBalls[i].active) {
                BlackBalls[i]._mySprite.draw(_G, BlackBalls[i]._mySprite.get_destRect());
            }
        }
    }

    public int getPoints(){ return _ballsTaken; }

    public void setActualPlayer(String s) { actualPlayer = s; }

}
