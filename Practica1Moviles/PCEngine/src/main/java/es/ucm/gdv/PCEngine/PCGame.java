package es.ucm.gdv.PCEngine;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.awt.image.BufferStrategy;
import java.util.List;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Input;
import es.ucm.gdv.engine.LogicInterface;

public class PCGame implements Game {

    PCGraphics _G;
    PCInput _input;
    LogicInterface _logic;

    BufferStrategy _strategy;
    java.awt.Graphics _awtGraphics;

    float deltaTime;
    long lastFrameTime;

    /**
     * Constructor. En él encontramos tras el init() el bucle ppal del juego en PC
     *
     * @param logic Lógica que va a utilizar el juego
     * @param width ancho de la ventana del juego
     * @param height alto de la ventana del juego
     *
     */
    public PCGame(LogicInterface logic, int width, int height){

        _G = new PCGraphics(width, height);
        _input = new PCInput(_G.get_ventana()); //Generamos el input con la ventana para recibir eventos
        _logic = logic;
        _logic.init(this);//inicializamos la lógica con el juego como parámetro

        deltaTime = 0.0f;
        lastFrameTime = System.nanoTime();

        mainLoop();
    }

    private void mainLoop(){
        //Bucle principal del juego
        while(true){
            deltaTime(); //Actualizamos el deltaTime
            handleEvent();
            update(deltaTime);
            render();
        }
    }

    /**
     * Utiliza la lista de eventos detectados y los manda uno a uno a la lógica para que
     * cada estado sólo se tenga de preocupar de un evento en lugar de toda la lista
     */
    private void handleEvent() {
        List<Input.TouchEvent> l = getInput().getTouchEvents();
        for(int i = 0; i < l.size(); i++) {
            Input.TouchEvent event = l.get(i);
            if( _G.mouseInsideScreen(event.get_x(), event.get_y()))
            {
                _logic.handleEvent(event);
            }

        }
    }

    public void update(float deltaTime){
        _logic.update(deltaTime);
    }

    /**
     * Bucle principal de renderizado de PC
     */
    public void render(){

        _strategy = _G.getBufferStrategy();

        do {
            do {
                _awtGraphics = _strategy.getDrawGraphics();
                _G.setGraphics(_awtGraphics);
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
        return _G;
    }

    @Override
    public Input getInput() {
        return _input;
    }

    public int getScreenWidth() {
        return _G.getWidth();
    }

    public int getScreenHeight() {
        return _G.getHeight();
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
