package es.ucm.gdv.engine;

public class Sprite {

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

    Image _image;
    Rect _rectSrc;

    public void draw(Graphics g){
        g.drawImage(_image);
    }
}
