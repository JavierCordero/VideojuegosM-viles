package es.ucm.gdv.Logic;

/**
 * Clase para realizar la función seno
 */

public class Sin {
    float sinFunct;
    int sinSpeed = 120;

    /**
     * Actualiza la función seno
     *
     * @param deltaTime Necesario para que la velocidad sea la misma en todos los equipos
     */
    public int updateSin(float deltaTime){

        sinFunct += sinSpeed * deltaTime;
        if((int)sinFunct >= 360) sinFunct = 0;
        return (int)(Math.sin(Math.toRadians((int)sinFunct)) * 10);
    }

}
