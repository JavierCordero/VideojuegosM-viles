package es.ucm.gdv.Logic;

import java.util.List;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Input;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.ResourceManager;
import es.ucm.gdv.engine.Sprite;
import es.ucm.gdv.engine.gameState;

public class playState extends gameState {

    Game _game;
    ResourceManager _rM;
    int colorMatch;
    String[] BGcolors = {"greenBG", "cyanBG", "blueBG", "darkBlueBG", "purpleBG", "greyBG", "orangeBG", "redBG", "browBG"};
    String actualPlayer = "whitePlayer";
    int BGspeed = 1;

    @Override
    public void init(Game game) {
        _game = game;
        _rM = new ResourceManager(_game);

        _rM.LoadImage("players", _game.getGraphics().newImage("players.png"));
        _rM.LoadImage("backgrounds", _game.getGraphics().newImage("backgrounds.png"));
        _rM.LoadImage("arrowsBackground", _game.getGraphics().newImage("arrowsBackground.png"));

        _rM.createSpriteFromImage("players",
                new Rect(0,528,0,384/2),
                "whitePlayer");

        _rM.createSpriteFromImage("players",
                new Rect(0,528,384/2,384),
                "blackPlayer");

        Image flechas = _rM.getImage("arrowsBackground");

        _rM.createSpriteFromImage("arrowsBackground",
                new Rect(0, flechas.getWidth(), 0, flechas.getHeight()),
                "BGArrow1");
        _rM.createSpriteFromImage("arrowsBackground",
                new Rect(0, flechas.getWidth(), 0, flechas.getHeight()),
                "BGArrow2");

        Graphics G = _game.getGraphics();

        _rM.getSprite("BGArrow1").set_destRect(new Rect((G.getWidth()/3)-_rM.getSprite("BGArrow1").getSpriteWidth()/3,
                (G.getWidth()/3)*2+_rM.getSprite("BGArrow1").getSpriteWidth()/3,
                0,
                G.getHeight()));

        _rM.getSprite("BGArrow2").set_destRect(new Rect((G.getWidth()/3)-_rM.getSprite("BGArrow2").getSpriteWidth()/3,
                (G.getWidth()/3)*2+_rM.getSprite("BGArrow2").getSpriteWidth()/3,
                -G.getHeight(),
                0));

        for(int i = 0; i < BGcolors.length; i++){
            _rM.createSpriteFromImage("backgrounds",
                    new Rect((288/9)*i,(288/9)*(i+1),0,32),
                    BGcolors[i]);
        }
        colorMatch = (int) (Math.random() * BGcolors.length-1) + 1;
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> l = _game.getInput().getTouchEvents();
        for(int i = 0; i < l.size(); i++){
            Input.TouchEvent event = l.get(i);
            if(event.getEvent() == Input.EventType.TOUCH)
                cambiaPlayer();
        }

        Graphics G = _game.getGraphics();

        Sprite backArrow = _rM.getSprite("BGArrow1");
        backArrow.set_destRect(new Rect(backArrow.get_destRect().get_left(),
                backArrow.get_destRect().get_right(),
                backArrow.get_destRect().get_top() + (int)(BGspeed * deltaTime),
                backArrow.get_destRect().get_bottom() + (int)(BGspeed * deltaTime)));

        if(backArrow.get_destRect().get_top() >= G.getHeight())
            backArrow.set_destRect(new Rect(backArrow.get_destRect().get_left(),
                    backArrow.get_destRect().get_right(),
                    -G.getHeight(),
                    0));

        Sprite backArrow2 = _rM.getSprite("BGArrow2");
        backArrow2.set_destRect(new Rect(backArrow2.get_destRect().get_left(),
                backArrow2.get_destRect().get_right(),
                backArrow2.get_destRect().get_top() + (int)(BGspeed * deltaTime),
                backArrow2.get_destRect().get_bottom() + (int)(BGspeed * deltaTime)));

        if(backArrow2.get_destRect().get_top() >= G.getHeight())
            backArrow2.set_destRect(new Rect(backArrow2.get_destRect().get_left(),
                    backArrow2.get_destRect().get_right(),
                    - G.getHeight(),
                    0));

    }
    @Override
    public Boolean render() {


        Graphics G = _game.getGraphics();
        G.clear(0xFF000000);
        _rM.getSprite(BGcolors[colorMatch]).draw(G, new Rect(0,1080,0,1920));

        Sprite backArrow = _rM.getSprite("BGArrow1");
        backArrow.draw(G, backArrow.get_destRect());

        Sprite backArrow2 = _rM.getSprite("BGArrow2");
        backArrow2.draw(G, backArrow2.get_destRect());

        _rM.getSprite(actualPlayer).draw(G,
                new Rect((G.getWidth()/2)-_rM.getSprite("whitePlayer").getSpriteWidth()/2,
                        (G.getWidth()/2)+_rM.getSprite("whitePlayer").getSpriteWidth()/2,
                        1200-_rM.getSprite("whitePlayer").getSpriteHeight()/2,
                        1200+_rM.getSprite("whitePlayer").getSpriteHeight()/2));
        // _resourceManager.getSprite("whitePlayer").draw(_game.getGraphics(),   new Rect(540-264,540+264,1200,1397));

        return true;
    }

    void cambiaPlayer(){
        if(actualPlayer == "whitePlayer") actualPlayer = "blackPlayer";
        else actualPlayer = "whitePlayer";
    }
}
