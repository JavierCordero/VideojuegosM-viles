package es.ucm.gdv.engine;

import java.util.HashMap;
import java.util.Map;

public class StatesManager {
    Map<String, State> states = new HashMap<String, State>();
    State _actualState;
    Game _game;

    public StatesManager(Game game){
        _game = game;
    }

    public void addState(State s, String name){
        states.put(name, s);
    }

    public void chState(String stateID){
        if(states.containsKey(stateID)){
            _actualState = states.get(stateID);
            _actualState.init(_game);
        }
    }

    public State get_state_by_name(String stateID){
        if(states.containsKey(stateID)){
            return states.get(stateID);
        }

        else return null;
    }

    public State getActualState(){
        return _actualState;
    }

}
