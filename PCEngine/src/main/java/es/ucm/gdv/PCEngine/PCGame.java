package es.ucm.gdv.PCEngine;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.awt.image.BufferStrategy;
import java.util.List;

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

    float deltaTime;
    long lastFrameTime;

    public PCGame(LogicInterface logic, int width, int height){

        _graphics = new PCGraphics(width, height);
        _input = new PCInput(_graphics.get_ventana());
        _logic = logic;
        _logic.init(this);

        deltaTime = 0.0f;
        lastFrameTime = System.nanoTime();

        while(true){
            deltaTime(); //Actualizamos el deltaTime
            handleEvent();
            update(deltaTime);
            render();
        }
    }

    private void handleEvent() {
        List<Input.TouchEvent> l = getInput().getTouchEvents();
        _logic.handleEvent(l);
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
        return _input;
    }

    public int getScreenWidth() {
        return _graphics.getWidth();
    }

    public int getScreenHeight() {
        return _graphics.getHeight();
    }

    /**
     * actualiza el deltaTime
     */
    public void deltaTime(){
        long currentTime = System.nanoTime();
        long nanoElapsedTime = currentTime - lastFrameTime;
        lastFrameTime = currentTime;
        deltaTime = (float) (nanoElapsedTime / 1.0E9);
    }
}
