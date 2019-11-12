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

    StatesManager _statesManager;

    //Valores dados en el enunciado
    int tapToPlayY = 950;
    int BGspeed = 384;
    int logoY = 356;

    public mainMenuState(StatesManager statesManager, ResourceManager resourceManager){
        _statesManager = statesManager;
        _rM = resourceManager;
    }

    @Override
    public void init(Game game) {
        _game = game;

        //colorMatch = (int) (Math.random() * BGcolors.length-1) + 1;
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> l = _game.getInput().getTouchEvents();
        for(int i = 0; i < l.size(); i++){
            Input.TouchEvent event = l.get(i);
            if(event.getEvent() == Input.EventType.TOUCH)
                _statesManager.chState("instrState");
        }

        Graphics G = _game.getGraphics();

        Sprite backArrow = _rM.getSprite("BGArrow1");
        Rect bacArrowRect = backArrow.get_destRect();

        if(bacArrowRect.get_top() > G.getHeight())
            backArrow.set_destRect(new Rect(bacArrowRect.get_left(),
                    bacArrowRect.get_right(),
                    -bacArrowRect.get_top() -bacArrowRect.get_bottom(),
                    0));
        else {
            backArrow.set_destRect(new Rect(bacArrowRect.get_left(),
                    bacArrowRect.get_right(),
                    bacArrowRect.get_top() + (int) (BGspeed * deltaTime),
                    bacArrowRect.get_bottom() + (int) (BGspeed * deltaTime)));
        }


       /* Sprite backArrow2 = _rM.getSprite("BGArrow2");
        Rect bacArrowRect2 = backArrow2.get_destRect();

        if(bacArrowRect2.get_top() > G.getHeight())
            backArrow2.set_destRect(new Rect(bacArrowRect2.get_left(),
                    bacArrowRect2.get_right(),
                    -bacArrowRect2.get_top() -bacArrowRect2.get_bottom(),
                    0));
        else {
            backArrow2.set_destRect(new Rect(bacArrowRect2.get_left(),
                    bacArrowRect2.get_right(),
                    bacArrowRect2.get_top() + (int) (BGspeed * deltaTime),
                    bacArrowRect2.get_bottom() + (int) (BGspeed * deltaTime)));
        }*/
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

        Sprite logo = _rM.getSprite("logo");

        logo.draw(G, new Rect((G.getWidth()/2)-logo.getSpriteWidth()/2,
                (G.getWidth()/2)+logo.getSpriteWidth()/2
                ,logoY,
                logoY + logo.getSpriteHeight()));

        Sprite tapToPlay = _rM.getSprite("ToPlay");

        tapToPlay.draw(G, new Rect((G.getWidth()/2)-tapToPlay.getSpriteWidth()/2,
                (G.getWidth()/2)+tapToPlay.getSpriteWidth()/2
                    ,tapToPlayY,
                tapToPlayY + tapToPlay.getSpriteHeight()));

        return true;
    }
}
