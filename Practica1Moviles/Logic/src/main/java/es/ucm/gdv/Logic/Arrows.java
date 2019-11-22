package es.ucm.gdv.Logic;

import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.ResourceManager;
import es.ucm.gdv.engine.Sprite;

public class Arrows {
    int prioridad = 0; //Determina cual es la imagen de la flecha que se encuentra arriba o abajo
    int BGspeed = 384; //velocidad de las flechas
    ResourceManager _rM;
    Graphics _G;

    Sprite backArrow;
    Sprite backArrow2;

    public void init(ResourceManager rm, Graphics g){
        _rM = rm;
        _G = g;
        backArrow = _rM.getSprite("BGArrow1");
        backArrow2 = _rM.getSprite("BGArrow2");
    }

    public void changePriority(){
        if(prioridad == 0)
            prioridad = 1;
        else prioridad = 0;
    }

    public int getPriority(){
        return prioridad;
    }

    //Dibuja las flechas del fondo en base a sus sprites
    public void draw(float deltaTime){

        Rect bacArrowRect = backArrow.get_destRect();
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
