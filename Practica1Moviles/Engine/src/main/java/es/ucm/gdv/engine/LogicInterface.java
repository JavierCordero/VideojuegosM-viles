package es.ucm.gdv.engine;

import java.util.List;

public interface LogicInterface {
    public void init(Game game);

    /**
     * Bucle principal de input de juego
     * @param event
     */
    public void handleEvent(Input.TouchEvent event);

    /**
     * Bucle principal de la lógica de juego
     * @param deltaTime
     */
    public void update(float deltaTime);

    /**
     * Bucle principal de la lógica de juego
     * @return true si no ha habido fallos
     */
    public Boolean render();
}
