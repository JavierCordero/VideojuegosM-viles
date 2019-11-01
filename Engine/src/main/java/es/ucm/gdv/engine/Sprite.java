package es.ucm.gdv.engine;

public class Sprite {

    Image _image;
    Rect _rectSrc;
    int _x;
    int _y;

    /**
     * Constructora de Sprite
     *
     * @param img imagen a dibujar. Puede ser un sritesheet
     * @param src rectangulo que se toma de la imagen para seleccionar el sprite del spritesheet
     */
    public Sprite(Image img, Rect src){
        _image = img;
        _rectSrc = src;
        _x = 0;
        _y = 0;
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
        _x = x;
        _y = y;
    }

    public void draw(Graphics g){
        g.drawImage(_image, _rectSrc,_x,_y,255);
    }
}
