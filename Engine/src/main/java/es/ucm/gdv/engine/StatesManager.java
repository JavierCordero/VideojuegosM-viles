package es.ucm.gdv.engine;

import java.util.HashMap;
import java.util.Map;

public class StatesManager {
    Map<String, State> states = new HashMap<String, State>();
    State _actualState;
    State _quedState;
    Game _game;

    public StatesManager(Game game){
        _game = game;
    }

    /**
     * Añade un nuevo estado al diccionario de estados
     * @param s Estado
     * @param name Nombre del estado
     */
    public void addState(State s, String name){
        states.put(name, s);
    }

    /**
     * Cambia entre estados
     * @param stateID Nombre del estado a cambiar
     */
    public void changeState(String stateID){
        if(states.containsKey(stateID)){ //Primero comprobamos que el estado que queremos cambiar existe
            _actualState = states.get(stateID);
            _quedState = null; //Ya no tendremos ningún estado apilado para pendientes
            _actualState.init(_game);
        }
    }

    /**
     * Busca un estado en base a su nombre
     * @param stateID nombre del estado a buscar
     * @return null si no se encuentra, el estado correspondiente en caso contrario
     */
    public State get_state_by_name(String stateID){
        if(states.containsKey(stateID)){
            return states.get(stateID);
        }

        else return null;
    }

    public State getActualState(){
        return _actualState;
    }

    public State getQuedState(){
        return _quedState;
    }

    /**
     * Añade un nuevo estado a la cola de los estados que tenemos pendientes por cambiar
     * @param name Nombre del estado
     */
    public void enqueueState(String name) {
        if (states.containsKey(name)) {
            _quedState = states.get(name);
        }
    }
}
