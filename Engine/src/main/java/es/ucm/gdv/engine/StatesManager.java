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
        s.init(_game);
        states.put(name, s);
    }

    public void chState(String stateID){
        if(states.containsKey(stateID))
            _actualState = states.get(stateID);
    }

    public State getActualState(){
        return _actualState;
    }

}
