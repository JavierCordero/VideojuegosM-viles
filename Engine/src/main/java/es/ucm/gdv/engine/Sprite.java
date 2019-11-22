package es.ucm.gdv.engine;

public class Sprite {

    Image _image;
    Rect _rectSrc;
    Rect _rectDest;
    int _alpha;

    /**
     * Constructora de Sprite
     *
     * @param img imagen a dibujar. Puede ser un sritesheet
     * @param src rectangulo que se toma de la imagen para seleccionar el sprite del spritesheet
     */
    public Sprite(Image img, Rect src, int alpha){
        _image = img;
        _rectSrc = src;
        _alpha = alpha;
    }
    /**
     * Constructora de Sprite
     *
     * @param img imagen a dibujar. Puede ser un sritesheet
     * @param src rectangulo que se toma de la imagen para seleccionar el sprite del spritesheet
     * @param x posición x del sprite
     * @param y posición y del sprite
     */
    public Sprite(Image img, Rect src, int x, int y, int _alpha){
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
        g.drawImage(_image, _rectSrc,dest, _alpha);
    }

    /**
     * Método para modificar el alpha de un sprite
     *
     * @param a valor en el que se va a modificar el alpha
     */
    public void modifyAlpha(int a){
        _alpha += a;
        if(_alpha < 0) _alpha = 0;
        else if(_alpha > 255) _alpha = 255;
    }

    /**
     * Método para cambiar el alpha de un sprite
     *
     * @param a nuevo valor del alpha del sprite
     */
    public void setAlpha(int a){
        _alpha = a;
    }

    /**
     * Devuelve el alpha correspondiente al sprite
     */
    public int getAlpha(){
        return _alpha;
    }
}
