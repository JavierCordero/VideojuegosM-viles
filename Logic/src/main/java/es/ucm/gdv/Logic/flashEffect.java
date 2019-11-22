package es.ucm.gdv.Logic;

import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.ResourceManager;
import es.ucm.gdv.engine.Sprite;

public class flashEffect {
    Sprite _whiteSprite;
    ResourceManager _rM;
    Graphics _G;
    int initialAlphaValue = 255;
    int alphaModifier = -20;

    public void init(ResourceManager rm, Graphics g){
        _rM = rm;
        _G = g;
        _whiteSprite = _rM.getSprite("white");
        _whiteSprite.setAlpha(initialAlphaValue); //Valor máximo del alpha
    }

    public void draw(){
        _whiteSprite.draw(_G, _whiteSprite.get_destRect());
    }

    /**
     * cambia el alpha al valor establecido "alphaModifier"
     */
    public void changeAlpha(){
        if(_whiteSprite.getAlpha() > 0)
            _whiteSprite.modifyAlpha(alphaModifier);
    }

}
