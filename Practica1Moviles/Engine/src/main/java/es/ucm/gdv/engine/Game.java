package es.ucm.gdv.engine;
public interface Game {
    /**
     * getter para conocer la instancia de la clase gráfica de la aplicación
     */
    public Graphics getGraphics();

    /**
     * getter para conocer la instancia de la clase de input de la aplicación
     */
    public Input getInput();
}
