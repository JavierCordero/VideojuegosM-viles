package es.ucm.gdv.PCEngine;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Input;
import es.ucm.gdv.engine.LogicInterface;

public class PCGame implements Game {

    PCGraphics _graphics;
    PCInput _input;
    LogicInterface _logic;

    public PCGame(LogicInterface logic){
        _graphics = new PCGraphics();
        _input = new PCInput();
        _logic = logic;
        _logic.init(this);

        while(true){
            render();
        }
    }

    public void update(float deltaTime){
        _logic.update(deltaTime);

    }

    public void render(){
        _logic.render();
    }

    @Override
    public Graphics getGraphics() {
        return _graphics;
    }

    @Override
    public Input getInput() {
        return null;
    }

    public int getScreenWidth() {
        return _graphics.getWidth();
    }

    public int getScreenHeight() {
        return _graphics.getHeight();
    }
}
