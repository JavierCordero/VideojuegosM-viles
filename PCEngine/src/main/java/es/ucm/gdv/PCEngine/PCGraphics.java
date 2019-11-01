package es.ucm.gdv.PCEngine;

import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.Sprite;
import java.awt.image.*;
import javax.swing.JFrame;
import java.awt.*;

public class PCGraphics implements Graphics {


    public class Ventana extends JFrame {
        int xSize = 800, ySize = 600;

        PCGraphics _graphics;

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

            setSize(xSize,ySize);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //_graphics = new PCGraphics(ventana);

            return true;
        } //  init
    }

    BufferStrategy _strategy;
    Ventana _ventana;
    java.awt.Graphics _graphics;

    public PCGraphics(){

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
        return null;
    }

    @Override
    public Sprite createSprite(Image img, Rect src) {
        return null;
    }

    @Override
    public void clear(int color) {

        do {
            do {
                _graphics = _strategy.getDrawGraphics();
                try {
                    _graphics.setColor(Color.blue);
                    _graphics.fillRect(0, 0, getWidth(), getHeight());
                }
                finally {
                    _graphics.dispose();
                }
            } while(_strategy.contentsRestored());
            _strategy.show();
        } while(_strategy.contentsLost());
    }

    @Override
    public void drawImage(Image image, int x, int y, int alpha) {

    }

    @Override
    public void drawImage(Image image, Rect src, int x, int y, int alpha) {

    }

    @Override
    public void drawImage(Image image, Rect src, Rect dest, int alpha) {

    }

    public void drawImage(Image image) {

    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
