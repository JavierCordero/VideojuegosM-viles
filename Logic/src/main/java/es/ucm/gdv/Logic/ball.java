package es.ucm.gdv.Logic;

import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.ResourceManager;
import es.ucm.gdv.engine.Sprite;

public class ball {
    boolean active;
    Sprite _mySprite;
    String _myColor;
    String actualBall = "whiteBall";
    int numBalls = 5;
    ResourceManager _rM;
    int numSprites = 0;
    Graphics _G;
    int ballSize = 128;

    playState.ball[] usedBalls = new playState.ball[numBalls];

    public ball(Sprite sprite){
        _mySprite = sprite;
    }

    public void setColor (String c){_myColor = c;}
     
    public void changeBallSprite(Sprite newSprite){
        _mySprite = newSprite;
    }

    public Sprite getBallSprite(){
        return _mySprite;
    }

    public String get_myColor(){
        return _myColor;
    }


    public void cambiaBall(){
        if(actualBall == "whiteBall") actualBall = "blackBall";
        else actualBall = "whiteBall";
    }

    public playState.ball firstUnusedBall(){
        int i;
        for(i = 0; i < usedBalls.length - 1; i++){
            if(!usedBalls[i].active)
                break;
        }
        return usedBalls[i];
    }

    public playState.ball newRandomBall(playState.ball b){
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
    }
}
