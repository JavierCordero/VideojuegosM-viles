package es.ucm.gdv.engine;

public interface Graphics {
    /**
     * Genera una nueva imagen.
     * @param name nombre de la imagen
     */
    public Image newImage(String name);

    /**
     * Limpia la pantalla con un nuevo color
     *
     * @param Color color entero del fondo
     */
    public void clear(int Color);

    /**
     * Pinta un nuevo color en el rectangulo dado
     *
     * @param Color color entero del fondo
     * @param  dest rectángulo en el que pintar
     */
    public void drawColor(int Color, Rect dest);

    /**
     *Pinta una imagen COMPLETA en las coordenadas dadas, con el alphaque se establezca
     *
     * @param image Imagen para pintar
     * @param x Posición x de la imagen
     * @param y Posición y de la imagen
     * @param alpha alfa de la imagen
     */
    public void drawImage(Image image, int x, int y, int alpha);

    /**
     *Pinta una rectángulo de una imagen en las coordenadas dadas, con el alphaque se establezca
     *
     * @param image Imagen para pintar
     * @param src Origen de la imagen para pintar
     * @param x Posición x de la imagen
     * @param y Posición y de la imagen
     * @param alpha alfa de la imagen
     */
    public void drawImage(Image image, Rect src, int x, int y, int alpha);

    /**
     *Pinta una rectángulo de una imagen en el rectangulo dado, con el alphaque se establezca
     *
     * @param image Imagen para pintar
     * @param src Origen de la imagen para pintar
     * @param dest Destino de la imagen para pintar
     * @param alpha alfa de la imagen
     */
    public void drawImage(Image image, Rect src, Rect dest, int alpha);

    /**
     * Getter para el ancho lógico de la pantalla
     *
     * @return  ancho logico de la pantalla
     */
    public int getWidth();

    /**
     * Getter para el alto lógico de la pantalla
     *
     * @return  alto logico de la pantalla
     */
    public int getHeight();

    /**
     * Getter para el ancho físico de la pantalla
     *
     * @return  ancho físico de la pantalla
     */
    public int getPhysicalWidth();

    /**
     * Getter para el alto físico de la pantalla
     *
     * @return  alto físico de la pantalla
     */
    public int getPhysicalHeight();

    /**
     * Getter para el factor de reescalado de la pantalla
     *
     * @return  factor de reescalado
     */
    public float getScaleFactor();

    /**
     * Reescala el rectangulo dado a coordenadas de la pantalla
     *
     * @param dest Rectangulo a reescalar
     * @return Rectangulo reescalado
     */
    public Rect rescaleRect(Rect dest);

    /**
     * Comprueba si el ratón está dentro del rectángulo dado
     *
     * @param x posición x del ratón
     * @param y posición y del ratón
     * @param rect Rectangulo que se quiere comprorbar
     *
     * @return true si está dentro, false en otro caso
     */
    public boolean mouseInsideRect(int x, int y, Rect rect);
}
