package es.ucm.gdv.Logic;

import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.ResourceManager;
import es.ucm.gdv.engine.Sprite;

public class Arrows {
    int prioridad = 0;
    int BGspeed = 384;
    ResourceManager _rM;
    Graphics _G;

    public void init(ResourceManager rm, Graphics g){
        _rM = rm;
        _G = g;
    }

    public void changePriority(){
        if(prioridad == 0)
            prioridad = 1;
        else prioridad = 0;
    }

    public int getPriority(){
        return prioridad;
    }

    public void draw(float deltaTime){
        Sprite backArrow = _rM.getSprite("BGArrow1");
        Rect bacArrowRect = backArrow.get_destRect();

        Sprite backArrow2 = _rM.getSprite("BGArrow2");
        Rect bacArrowRect2 = backArrow2.get_destRect();

        if(bacArrowRect.get_top() >= _G.getHeight()) {
            backArrow.set_destRect(new Rect(bacArrowRect.get_left(),
                    bacArrowRect.get_right(),
                    -_G.getHeight() + bacArrowRect2.get_top() + (_G.getHeight() - bacArrowRect.get_top()) - 1,
                    bacArrowRect2.get_top() - 1));

            backArrow.set_destRect(new Rect(bacArrowRect.get_left(),
                    bacArrowRect.get_right(),
                    (bacArrowRect2.get_top() - _G.getHeight() + (BGspeed * deltaTime)),
                    (bacArrowRect2.get_top() + (BGspeed * deltaTime))));

            changePriority();
        }
        else if(getPriority() == 0) {
            backArrow.set_destRect(new Rect(bacArrowRect.get_left(),
                    bacArrowRect.get_right(),
                    (bacArrowRect.get_top() + (BGspeed * deltaTime)),
                    (bacArrowRect.get_bottom() + (BGspeed * deltaTime))));

        }
        else {
            backArrow.set_destRect(new Rect(bacArrowRect.get_left(),
                    bacArrowRect.get_right(),
                    (bacArrowRect2.get_top() - _G.getHeight() + (BGspeed * deltaTime)),
                    (bacArrowRect2.get_top() + (BGspeed * deltaTime))));
        }



        if(bacArrowRect2.get_top() >= _G.getHeight()) {
            backArrow2.set_destRect(new Rect(bacArrowRect2.get_left(),
                    bacArrowRect2.get_right(),
                    -_G.getHeight()+bacArrowRect.get_top()+(_G.getHeight()-bacArrowRect2.get_top())-1,
                    bacArrowRect.get_top()-1));

            backArrow2.set_destRect(new Rect(bacArrowRect2.get_left(),
                    bacArrowRect2.get_right(),
                    (bacArrowRect.get_top() - _G.getHeight() + (BGspeed * deltaTime)),
                    (bacArrowRect.get_top() + (BGspeed * deltaTime))));

            changePriority();
        }
        else if(getPriority() == 1) {
            backArrow2.set_destRect(new Rect(bacArrowRect2.get_left(),
                    bacArrowRect2.get_right(),
                    bacArrowRect2.get_top() +  (BGspeed * deltaTime),
                    bacArrowRect2.get_bottom() + (BGspeed * deltaTime)));
        }
        else {
            backArrow2.set_destRect(new Rect(bacArrowRect2.get_left(),
                    bacArrowRect2.get_right(),
                    (bacArrowRect.get_top() - _G.getHeight() + (BGspeed * deltaTime)),
                    (bacArrowRect.get_top() + (BGspeed * deltaTime))));
        }
    }
}
