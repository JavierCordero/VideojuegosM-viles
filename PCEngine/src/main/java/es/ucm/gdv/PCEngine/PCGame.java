package es.ucm.gdv.PCEngine;

import java.awt.image.BufferStrategy;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Input;
import es.ucm.gdv.engine.LogicInterface;

public class PCGame implements Game {

    PCGraphics _graphics;
    PCInput _input;
    LogicInterface _logic;

    BufferStrategy _strategy;
    java.awt.Graphics _awtGraphics;

    public PCGame(LogicInterface logic, int width, int height){

        _graphics = new PCGraphics(width, height);
        _input = new PCInput();
        _logic = logic;
        _logic.init(this);

        while(true){
            update(1); //Hay que cambiarlo evidentemente
            render();
        }
    }

    public void update(float deltaTime){
        _logic.update(deltaTime);
    }

    public void render(){

        _strategy = _graphics.getBufferStrategy();

        do {
            do {
                _awtGraphics = _strategy.getDrawGraphics();
                _graphics.setGraphics(_awtGraphics);
                try {
                    _logic.render();
                }
                finally {
                    _awtGraphics.dispose();
                }
            } while(_strategy.contentsRestored());
            _strategy.show();
        } while(_strategy.contentsLost());
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
