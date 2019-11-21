package es.ucm.gdv.Logic;

import java.util.Random;

import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.ResourceManager;
import es.ucm.gdv.engine.Sprite;

class ball{
    boolean active = false;
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

public class ballManager {
    ResourceManager _rM;
    Graphics _G;


    int _originalBallSpeed = 430;
    int _ballsSpeed;
    int ballSeparation = 395;
    int incrBallSpeed = 90;
    int ballSize = 128;

    int _ballsTaken;
    int numBalls = 5;
    ball[] WhiteBalls = new ball[numBalls];
    ball[] BlackBalls = new ball[numBalls];
    ball lastBallCreated;

    public ballManager(ResourceManager resourceManager, Graphics graphics){
        _rM = resourceManager;
        _G = graphics;
        _ballsTaken = 0;
        _ballsSpeed = _originalBallSpeed;


        Image balls = _rM.getImage("balls");
        for(int i = 0; i < numBalls; i++) {
            _rM.createSpriteFromImage("balls",
                    new Rect(0, 1280 / 10, 0, balls.getHeight() / 2),
                    "whiteBall" + i, new Random().nextInt(70 + 120));

            _rM.createSpriteFromImage("balls",
                    new Rect(0, 1280 / 10, 0, balls.getHeight() / 2),
                    "blackBall" + i, new Random().nextInt(70 + 120));


            WhiteBalls[i] = new ball(_rM.getSprite("whiteBall"+i));
            BlackBalls[i] = new ball(_rM.getSprite("blackBall"+i));
        }
    }

 /*   void cambiaBall(){
        if(actualBall == "whiteBall") actualBall = "blackBall";
        else actualBall = "whiteBall";
    }

    playState.ball firstUnusedBall(){
        int i;
        for(i = 0; i < usedBalls.length - 1; i++){
            if(!usedBalls[i].active)
                break;
        }
        return usedBalls[i];
    }

    playState.ball newRandomBall(playState.ball b){
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
        numSprites++;
        return b;
    }*/

}
