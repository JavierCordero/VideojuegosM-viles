package es.ucm.gdv.engine;

public abstract class RescaleGraphics implements Graphics {


    protected static final int LogicalWidth = 1080;
    protected static final int LogicalHeight = 1920;
    protected static final float AspectRatio = LogicalWidth/LogicalHeight;
    int _screenWidth;
    int _screenHeight;
    float ScaleFactor;
    int diffX;
    int diffY;

    public RescaleGraphics(int screenWidth, int screenHeight){
        _screenHeight = screenHeight;
        _screenWidth = screenWidth;

        diffX = _screenWidth - LogicalWidth;
        diffY = _screenHeight - LogicalHeight;
        float scaleW = (float)_screenWidth/ (float)LogicalWidth;
        float scaleH = (float)_screenHeight/ (float)LogicalHeight;
        if(scaleW < scaleH)
            ScaleFactor = scaleW;
        else ScaleFactor = scaleH;

    }

    /**
     * Dibuja una imagen completa en pantalla en coordenadas x, y
     *
     * @param image Imagen donde se encuentra el sprite a dibujar
     * @param x coordenada x donde se dibujara el primer pixel
     * @param y coordenada y donde se dibujara el primer pixel
     * @param alpha transparencia del sprite
     */
    @Override
    public void drawImage(Image image, int x, int  y, int alpha) {
        Rect src = new Rect(0, image.getWidth(), 0, image.getHeight());
        Rect dest = new Rect(x,x+src.get_right(), y, y+src.get_bottom());

        finalDrawImage(image,src,dest);
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
        //if(_screenWidth > LogicalWidth){ dest.set_left((_screenWidth/2 - LogicalWidth/2)); dest.set_right((_screenWidth/2 + LogicalWidth/2)); }
        //if(_screenHeight > LogicalHeight){ dest.set_top(dest.get_top()-diffY); }
        Rect newDest = new Rect(0,0,0,0);

        int newStartLeft = (int)(_screenWidth - (LogicalWidth*ScaleFactor))/2;
        int newStartTop = (int)(_screenHeight - (LogicalHeight*ScaleFactor))/2;

        newDest.set_left(newStartLeft+(int)(dest.get_left()*ScaleFactor));
        newDest.set_right((int)(dest.get_right()*ScaleFactor)+newStartLeft);
        newDest.set_top(newStartTop+(int)(dest.get_top()*ScaleFactor));
        newDest.set_bottom((int)(dest.get_bottom()*ScaleFactor)+newStartTop);

        finalDrawImage(image, src, newDest);
    }


    //ESTOS METODOS DEBERAN IMPLEMENTARSE AQUI LUEGO CON EL ESCALADO
    @Override
    public int getWidth() {
        return _screenWidth;
    }

    @Override
    public int getHeight() {
        return _screenHeight;
    }

    protected void finalDrawImage(Image img, Rect dest, Rect src){
    } //finalDrawImage
}
