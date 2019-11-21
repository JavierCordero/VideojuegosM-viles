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

    /**
     * Constructora de los parametros iniciales asi como la hebra de juego
     *
     * @param ac La activiadad en ejecucion
     * @param logic La logica de juego que va a actualizar
     * @param screenWidth Ancho del dispositivo
     * @param screenHeight Alto del dispositivo
     */
    public AndroidGame(AppCompatActivity ac, LogicInterface logic, int screenWidth, int screenHeight){
        _gameFlow = new GameFlow(ac);
        _graphics = new AndroidGraphics(_gameFlow, ac.getAssets(), screenWidth, screenHeight);
        _input = new AndroidInput(_graphics._surfaceView);
        _logic = logic;
        _logic.init(this);
    }

    /**
     * Modifica los valores de ancho y alto tanto logicos como fisicos
     * para ajustar el escalado de los objetos con el cambio de pantalla
     *
     * @param LogicalW Nuevo ancho logico
     * @param LogicalH Nuevo alto logico
     * @param screenW Nuevo ancho fisico
     * @param screenH Nuevo alto fisico
     */
    public void ScreenOrientation(int LogicalW, int LogicalH, int screenW, int screenH){
        (_graphics).setLogicalScale(LogicalW, LogicalH, screenW, screenH);
    }

    /**
     * Devuelve la clase que gestiona la parte grafica del juego
     *
     * @return Android Graphics
     */
    @Override
    public Graphics getGraphics() {
        return _graphics;
    }

    /**
     * Devuelve la clase que gestiona el input del juego
     *
     * @return Android Input
     */
    @Override
    public Input getInput() {
        return _input;
    }

    /**
     * No es que el juego tenga todo el swing
     * sino que devuelve la hebra de flujo de juego
     *
     * @return devuelve el flujo de juego
     */
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

        /**
         * Pausa el bucle principal
         */

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

        /**
         * Actualiza la logica
         * @param deltaTime Tiempo entre frame y frame
         */
        public void update(float deltaTime){
            //Llama al update de la logica
            _logic.update(deltaTime);
        }

        /**
         * realiza un "resent" por pantalla
         */
        public void render(){
            //Llama al render de la logica
            while (!getHolder().getSurface().isValid())
                ;
            _graphics.lockCanvas();
            _logic.render();
            _graphics.freeCanvas();
        }

        /**
         * Metodo que ejecutará la hebra principal del juego
         */
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

        /**
         * actualiza el deltaTime
         */
        public void deltaTime(){
            long currentTime = System.nanoTime();
            long nanoElapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;
            deltaTime = (float) (nanoElapsedTime / 1.0E9);
        }
    } // class MyView
}
