package es.ucm.gdv.engine;

public abstract class RescaleGraphics implements Graphics {


    protected static int LogicalWidth = 1080;
    protected static int LogicalHeight = 1920;
    protected static float AspectRatio = LogicalWidth/LogicalHeight; //No se usa pero podría usarse en un futuro
    int _screenWidth;
    int _screenHeight;
    float ScaleFactor; //Numero por el que se multiplican los destinos para conocer sus posiciones reles
    int diffX;
    int diffY;


    /**
     * Constructora de RescaleGraphics
     *
     * @param screenWidth Ancho real de la pantalla
     * @param screenHeight Alto real de la pantalla
     */
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

    public void drawColor(int Color, Rect dest){
        Rect newDest = new Rect(0,0,0,0);

        int newStartLeft = (int)(_screenWidth - (LogicalWidth*ScaleFactor))/2;
        int newStartTop = (int)(_screenHeight - (LogicalHeight*ScaleFactor))/2;

        newDest.set_left(newStartLeft+(int)(dest.get_left()*ScaleFactor));
        newDest.set_right((int)(dest.get_right()*ScaleFactor)+newStartLeft);
        newDest.set_top(newStartTop+(int)(dest.get_top()*ScaleFactor));
        newDest.set_bottom((int)(dest.get_bottom()*ScaleFactor)+newStartTop);

        finalDrawColor(Color, newDest);
    };


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

        Rect newDest = new Rect(0,0,0,0);

        int newStartLeft = (int)(_screenWidth - (LogicalWidth*ScaleFactor))/2;
        int newStartTop = (int)(_screenHeight - (LogicalHeight*ScaleFactor))/2;

        newDest.set_left(newStartLeft+(int)(dest.get_left()*ScaleFactor));
        newDest.set_right((int)(dest.get_right()*ScaleFactor)+newStartLeft);
        newDest.set_top(newStartTop+(int)(dest.get_top()*ScaleFactor));
        newDest.set_bottom((int)(dest.get_bottom()*ScaleFactor)+newStartTop);

        finalDrawImage(image, src, newDest, alpha);
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

        Rect newDest = new Rect(0,0,0,0);

        int newStartLeft = (int)(_screenWidth - (LogicalWidth*ScaleFactor))/2;
        int newStartTop = (int)(_screenHeight - (LogicalHeight*ScaleFactor))/2;

        newDest.set_left(newStartLeft+(int)(dest.get_left()*ScaleFactor));
        newDest.set_right((int)(dest.get_right()*ScaleFactor)+newStartLeft);
        newDest.set_top(newStartTop+(int)(dest.get_top()*ScaleFactor));
        newDest.set_bottom((int)(dest.get_bottom()*ScaleFactor)+newStartTop);

        finalDrawImage(image, src, newDest, alpha);
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

        finalDrawImage(image, src, newDest, alpha);
    }


    /**
     * Devuelve el ancho Logico de la pantalla
     *
     * @return int
     */
    @Override
    public int getWidth() {
        return LogicalWidth;
    }

    /**
     * Devuelve el alto Logico de la pantalla
     *
     * @return int
     */
    @Override
    public int getHeight() {
        return LogicalHeight;
    }

    /**
     * Devuelve el ancho fisico de la pantalla
     *
     * @return int
     */
    @Override
    public int getPhysicalWidth() {
        return _screenWidth;
    }

    /**
     * Devuelve el alto fisico de la pantalla
     *
     * @return int
     */
    @Override
    public int getPhysicalHeight() { return _screenHeight; }

    abstract protected void finalDrawImage(Image img, Rect dest, Rect src, int alpha);
    abstract protected void finalDrawColor(int color, Rect dest);

    /**
     * Establece los nuevos parametros de pantalla
     *
     * @param LogicalW ancho logico
     * @param LogicalH alto logico
     * @param screenW ancho real sobre la pantalla
     * @param screenH alto real sobre la pantalla
     */
    public void setLogicalScale(int LogicalW, int LogicalH, int screenW, int screenH){
        LogicalWidth = LogicalW;
        LogicalHeight = LogicalH;
        _screenWidth = screenW;
        _screenHeight = screenH;

        diffX = _screenWidth - LogicalWidth;
        diffY = _screenHeight - LogicalHeight;
        float scaleW = (float)_screenWidth/ (float)LogicalWidth;
        float scaleH = (float)_screenHeight/ (float)LogicalHeight;
        if(scaleW < scaleH)
            ScaleFactor = scaleW;
        else ScaleFactor = scaleH;
    }
}
