package es.ucm.gdv.engine;

public class Sprite {

    Image _image;
    Rect _rectSrc;
    Rect _rectDest;


    /**
     * Constructora de Sprite
     *
     * @param img imagen a dibujar. Puede ser un sritesheet
     * @param src rectangulo que se toma de la imagen para seleccionar el sprite del spritesheet
     */
    public Sprite(Image img, Rect src){
        _image = img;
        _rectSrc = src;
    }
    /**
     * Constructora de Sprite
     *
     * @param img imagen a dibujar. Puede ser un sritesheet
     * @param src rectangulo que se toma de la imagen para seleccionar el sprite del spritesheet
     * @param x posición x del sprite
     * @param y posición y del sprite
     */
    public Sprite(Image img, Rect src, int x, int y){
        _image = img;
        _rectSrc = src;
    }

    public void set_destRect(Rect rectDest){
        _rectDest = rectDest;
    }

    public int getSpriteWidth(){
        return (int)_rectSrc._right - (int)_rectSrc._left;
    }

    public int getSpriteHeight(){
        return (int)_rectSrc._bottom - (int)_rectSrc._top;
    }

    public Rect get_destRect(){
        return _rectDest;
    }

    public void draw(Graphics g, Rect dest){
        g.drawImage(_image, _rectSrc,dest, 255);
    }
}
