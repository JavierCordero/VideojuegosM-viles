package es.ucm.gdv.PCEngine;

import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.RescaleGraphics;
import es.ucm.gdv.engine.Sprite;
import java.awt.image.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class PCGraphics extends RescaleGraphics {

    int _width, _height;
    public class Ventana extends JFrame {


        PCGraphics _graphics;
        JPanel panel;

        /**
         * Constructor.
         *
         * @param title Texto que se utilizará como título de la ventana
         * que se creará.
         */
        public Ventana(String title) {
            super(title);
        }

        /**
         * Realiza la inicialización del objeto (inicialización en dos pasos).
         * Se configura el tamaño de la ventana, se habilita el cierre de la
         * aplicación al cerrar la ventana, y se carga la imagen que se mostrará
         * en la ventana.
         *
         * Debe ser llamado antes de mostrar la ventana (con setVisible()).
         *
         * @return Cierto si todo fue bien y falso en otro caso (se escribe una
         * descripción del problema en la salida de error).
         */
        public boolean init() {

            setSize(_width, _height);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //_graphics = new PCGraphics(ventana);

            return true;
        } //  init
    }

    BufferStrategy _strategy;
    Ventana _ventana;
    java.awt.Graphics _graphics;

    public PCGraphics(int width, int height){
        super(width, height);

        _width = width; _height = height;
        System.out.println("Creando ventana...");
       _ventana = new Ventana("Mi juego super increible en 4K");
        if (!_ventana.init()) {
            //Ha fallado la inicialización.
            System.out.println("Ha fallado la inicialización de la ventana.");
            return;
        }

        System.out.println("Ventana creada con exito.");

        // Vamos a usar renderizado activo. No queremos que Swing llame al
        // método repaint() porque el repintado es continuo en cualquier caso.
        _ventana.setIgnoreRepaint(true);

        // Hacemos visible la ventana.
        _ventana.setVisible(true);

        // Intentamos crear el buffer strategy con 2 buffers.
        System.out.println("Intentamos crear el buffer startegy con 2 buffers");
        int intentos = 100;
        while(intentos-- > 0) {
            try {
                _ventana.createBufferStrategy(2);
                break;
            }
            catch(Exception e) {
            }
        } // while pidiendo la creación de la buffeStrategy
        if (intentos == 0) {
            System.err.println("No pude crear la BufferStrategy");
            return;
        }
        else {
            // En "modo debug" podríamos querer escribir esto.
            System.out.println("BufferStrategy creado tras " + (100 - intentos) + " intentos.");
        }

        // Obtenemos el Buffer Strategy que se supone acaba de crearse.
        _strategy = _ventana.getBufferStrategy();
    }

    @Override
    public Image newImage(String name) {
       PCImage _image = new PCImage(name);
       return _image;
    }

    //public Sprite createSprite(Image img, Rect src) {
        //return null;
    //}

    @Override
    public void clear(int color) {

        drawGraphics();

        Color c = new Color(color);
        _graphics.setColor(c);
        _graphics.fillRect(0, 0, getWidth(), getHeight());

        showGraphics();

    }

    private void drawGraphics(){
        _graphics = _strategy.getDrawGraphics();
    }

    private void showGraphics(){
        _graphics.dispose();
        _strategy.show();
    }

   /* public void draw(){
        do {
            do {
                _graphics = _strategy.getDrawGraphics();
                try {

                }
                finally {
                    _graphics.dispose();
                }
            } while(_strategy.contentsRestored());
            _strategy.show();
        } while(_strategy.contentsLost());
    }
    */

    protected void finalDrawImage(Image img, Rect src, Rect dest){
        //try {
        drawGraphics();

        java.awt.Image im = ((PCImage) img).get_image();

        if(im != null)
            _graphics.drawImage(im, dest.get_left(), dest.get_top(),
                    dest.get_right(), dest.get_bottom(),
                    src.get_left(), src.get_top(), src.get_left() + src.get_right(),
                    src.get_top() + src.get_bottom(), null);

        //}
        showGraphics();
       // catch(Exception e){System.out.println(e + " No se ha podido dibujar la imagen.");}
    }

    @Override
    public int getWidth() {
        return _width;
    }

    @Override
    public int getHeight() {
        return _height;
    }
}
