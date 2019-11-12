package es.ucm.gdv.Logic;

import java.util.List;

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
    //int colorMatch;
    //String[] BGcolors = {"greenBG", "cyanBG", "blueBG", "darkBlueBG", "purpleBG", "greyBG", "orangeBG", "redBG", "browBG"};

    StatesManager _statesManager;

    //Valores dados en el enunciado
    int tapToPlayY = 1464;
    int BGspeed = 384;
    int howToPlayY = 290;
    int instructionsY = 768;

    public instructionsState(StatesManager statesManager, ResourceManager resourceManager){
        _statesManager = statesManager;
        _rM = resourceManager;
    }

    @Override
    public void init(Game game) {
        _game = game;

        //colorMatch = (int) (Math.random() * BGcolors.length-1) + 1;
        //colorMatch = 0; //Color verde del array de colores
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> l = _game.getInput().getTouchEvents();
        for(int i = 0; i < l.size(); i++){
            Input.TouchEvent event = l.get(i);
            if(event.getEvent() == Input.EventType.TOUCH)
                _statesManager.chState("playState");
        }

        Graphics G = _game.getGraphics();

        Sprite backArrow = _rM.getSprite("BGArrow1");
        Rect bacArrowRect = backArrow.get_destRect();

        if(bacArrowRect.get_top() > G.getHeight())
            backArrow.set_destRect(new Rect(bacArrowRect.get_left(),
                    bacArrowRect.get_right(),
                    -bacArrowRect.get_top(),
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
                    -bacArrowRect.get_top(),
                    0));
        else {
            backArrow2.set_destRect(new Rect(bacArrowRect2.get_left(),
                    bacArrowRect2.get_right(),
                    bacArrowRect2.get_top() + (int) (BGspeed * deltaTime),
                    bacArrowRect2.get_bottom() + (int) (BGspeed * deltaTime)));
        }
    }

    @Override
    public Boolean render() {
        Graphics G = _game.getGraphics();
        G.clear(0xFF000000);
        //_rM.getSprite(BGcolors[colorMatch]).draw(G, new Rect(0,1080,0,1920));
        _rM.getSprite("greenBG").draw(G, new Rect(0,1080,0,1920));

        Sprite backArrow = _rM.getSprite("BGArrow1");
        backArrow.draw(G, backArrow.get_destRect());

        Sprite backArrow2 = _rM.getSprite("BGArrow2");
        backArrow2.draw(G, backArrow2.get_destRect());

        Sprite howToPlay = _rM.getSprite("howToPlay");

        howToPlay.draw(G, new Rect((G.getWidth()/2)-howToPlay.getSpriteWidth()/2,
                (G.getWidth()/2)+howToPlay.getSpriteWidth()/2
                ,howToPlayY,
                howToPlayY + howToPlay.getSpriteHeight()));

        Sprite instructions = _rM.getSprite("instructions");

        instructions.draw(G, new Rect((G.getWidth()/2)-instructions.getSpriteWidth()/2,
                (G.getWidth()/2)+instructions.getSpriteWidth()/2
                ,instructionsY,
                instructionsY + instructions.getSpriteHeight()));

        Sprite tapToPlay = _rM.getSprite("ToPlay");

        tapToPlay.draw(G, new Rect((G.getWidth()/2)-tapToPlay.getSpriteWidth()/2,
                (G.getWidth()/2)+tapToPlay.getSpriteWidth()/2
                ,tapToPlayY,
                tapToPlayY + tapToPlay.getSpriteHeight()));

        return true;
    }
}
