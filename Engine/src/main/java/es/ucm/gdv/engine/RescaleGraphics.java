package es.ucm.gdv.engine;

public abstract class RescaleGraphics implements Graphics {

    /**
     * Dibuja una imagen completa en pantalla en coordenadas x, y
     *
     * @param image Imagen donde se encuentra el sprite a dibujar
     * @param x coordenada x donde se dibujara el primer pixel
     * @param y coordenada y donde se dibujara el primer pixel
     * @param alpha transparencia del sprite
     */
    @Override
    public void drawImage(Image image, int x, int y, int alpha) {
       // finalDrawImage();
    }

    /**
     * Dibuja la fracción seleccionada de imagen en pantalla en coordenadas x, y
     *
     * @param image Imagen donde se encuentra el sprite a dibujar
     * @param src rectángulo de la imagen a dibujar (sprite)
     * @param x coordenada x donde se dibujara el primer pixel
     * @param y coordenada y donde se dibujara el primer pixel
     * @param alpha transparencia del sprite
     */
    @Override
    public void drawImage(Image image, Rect src, int x, int y, int alpha) {
        Rect dest = new Rect(x,x+src.get_right(), y, y+src.get_bottom());
        finalDrawImage(image, src, dest);
    }

    /**
     * Dibuja una fracción de imagen en pantalla la posición y tamaño del rectangulo dest
     *
     * @param image Imagen donde se encuentra el sprite a dibujar
     * @param src rectángulo de la imagen a dibujar (sprite)
     * @param dest rectangulo destino de dibujo, afecta escalado
     * @param alpha transparencia del sprite
     */
    @Override
    public void drawImage(Image image, Rect src, Rect dest, int alpha) {

    }


    //ESTOS METODOS DEBERAN IMPLEMENTARSE AQUI LUEGO CON EL ESCALADO
    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    protected void finalDrawImage(Image img, Rect dest, Rect src){

    } //finalDrawImage
}
