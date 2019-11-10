package es.ucm.gdv.Logic;

import es.ucm.gdv.engine.AbstractInput;
import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Input;
import es.ucm.gdv.engine.LogicInterface;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.ResourceManager;
import es.ucm.gdv.engine.Sprite;
import es.ucm.gdv.engine.gameState;
import sun.rmi.runtime.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.Map;

public class Logic implements LogicInterface {

    Map<String, gameState> states = new HashMap<String, gameState>();
    gameState ActualState;

    Game _game;
    ResourceManager _rM;

    public void init(Game game){
        _game = game;
        _rM = new ResourceManager(_game);

       gameState playState = new playState();
       playState.init(game);

        gameState playState2 = new playState();
        playState.init(game);

       states.put("playState", playState);

       ActualState = states.get("playState");
    }

    public void chState(String stateID){
        if(states.containsKey(stateID))
            ActualState = states.get(stateID);
    }

    @Override
    public void update(float deltaTime) {
        ActualState.update(deltaTime);
    }

    @Override
    public Boolean render() {
        ActualState.render();
        return true;
    }


}
