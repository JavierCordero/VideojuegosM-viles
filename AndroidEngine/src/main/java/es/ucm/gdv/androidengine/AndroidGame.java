package es.ucm.gdv.androidengine;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Vector;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Input;
import es.ucm.gdv.engine.LogicInterface;
import es.ucm.gdv.engine.RescaleGraphics;

public class AndroidGame implements Game {

    AndroidGraphics _graphics;
    Input _input;
    GameFlow _gameFlow;
    LogicInterface _logic;

    public AndroidGame(AppCompatActivity ac, LogicInterface logic, int screenWidth, int screenHeight){
        _gameFlow = new GameFlow(ac);
        _graphics = new AndroidGraphics(_gameFlow, ac.getAssets(), screenWidth, screenHeight);
        _input = new AndroidInput();
        _logic = logic;
        _logic.init(this);
    }

    public void ScreenOrientation(int LogicalW, int LogicalH, int screenW, int screenH){
        ((RescaleGraphics)_graphics).setLogicalScale(LogicalW, LogicalH, screenW, screenH);
    }

    @Override
    public Graphics getGraphics() {
        return _graphics;
    }

    @Override
    public Input getInput() {
        return _input;
    }


    public GameFlow getGameFlow() { return _gameFlow; }

    public class GameFlow extends SurfaceView implements Runnable{


        volatile boolean _running = false;
        Thread _renderThread;
        float deltaTime;
        long lastFrameTime;

        /**
         * Constructor.
         *
         * @param context Contexto en el que se integrará la vista
         *                (normalmente una actividad).
         */
        public GameFlow (Context context){
            super(context);
        }

        //Ciclo de vida: el bucle principal debe ser puesto en marcha de nuevo
        public void resume(){
            if(!_running) {
                _running = true;
                _renderThread = new Thread(this);
                _renderThread.start();
            }
        }

        //Ciclo de vida: el bucle principal debe ser parado temporalmente
        public void pause(){
            _running = false;
            // Esperar a que termine.
            while(true) {
                try {
                    _renderThread.join();
                    break;
                } catch (InterruptedException e) {

                }
            }
        }   // pause

        // Actualiza la lógica
        public void update(float deltaTime){
            //Llama al update de la logica
            _logic.update(deltaTime);
        }

        public void render(){
            //Llama al render de la logica
            while (!getHolder().getSurface().isValid())
                ;
            _graphics.lockCanvas();
            _logic.render();
            _graphics.freeCanvas();
        }

        public void run(){

            if (_renderThread != Thread.currentThread()) {
                // ¿¿Quién es el tuercebotas que está llamando al
                // run() directamente?? Programación defensiva
                // otra vez, con excepción, por merluzo.
                throw new RuntimeException("run() should not be called directly");
            }

            deltaTime = 0.0f;
            lastFrameTime = System.nanoTime();

            while(_running){

                deltaTime(); //Actualizamos el deltaTime
                update(deltaTime);
                render();
            } // Bucle principal del juego
        }

        public void onDraw(Canvas c){

        }

        public void deltaTime(){
            long currentTime = System.nanoTime();
            long nanoElapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;
            deltaTime = (float) (nanoElapsedTime / 1.0E9);
        }
    } // class MyView
}
