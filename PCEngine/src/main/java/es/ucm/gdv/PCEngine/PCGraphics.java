package es.ucm.gdv.PCEngine;

import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.RescaleGraphics;
import es.ucm.gdv.engine.Sprite;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.*;
import javax.swing.JFrame;
import java.awt.*;

public class PCGraphics extends RescaleGraphics {

    int _width, _height;
    int _logicalWidth = 1080, _logicalHeight = 1920;
    public class Ventana extends JFrame {


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

            setSize(_width, _height);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //setExtendedState(JFrame.MAXIMIZED_BOTH);
            //setUndecorated(true);

            addComponentListener(new ComponentAdapter()
            {
                public void componentResized(ComponentEvent evt) {
                    Component c = (Component)evt.getSource();
                    setLogicalScale(_logicalWidth, _logicalHeight, c.getSize().width, c.getSize().height);
                }
            });

            return true;
        } //  init
    }

    public Ventana get_ventana(){
        return _ventana;
    }

    Ventana _ventana;
    BufferStrategy _strategy;
    java.awt.Graphics _awtGraphics;

    public BufferStrategy getBufferStrategy(){
        return _strategy;
    }

    void setGraphics(java.awt.Graphics g){
        _awtGraphics = g;
    }

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

    @Override
    public void clear(int color) {

        Color c = new Color(color);
        _awtGraphics.setColor(c);
        _awtGraphics.fillRect(0, 0, getPhysicalWidth(), getPhysicalHeight());
    }

    public void finalDrawColor(int color, Rect dest){
        Color c = new Color(color);
        _awtGraphics.setColor(c);
       _awtGraphics.fillRect((int)dest.get_left(), (int)dest.get_top(), (int)dest.get_right()-(int)dest.get_left(), (int)dest.get_bottom()-(int)dest.get_top());
    }

    @Override
    protected void finalDrawImage(Image img, Rect src, Rect dest, int alpha){

        java.awt.Image im = ((PCImage) img).get_image();

        if(im != null) {
            Graphics2D g = (Graphics2D)_awtGraphics;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                    ((float)alpha / (float)255)));
            g.drawImage(im, (int) dest.get_left(), (int) dest.get_top(),
                    (int) dest.get_right(), (int) dest.get_bottom(),
                    (int) src.get_left(), (int) src.get_top(), (int) src.get_right(),
                    (int) src.get_bottom(), null);
        }
    }
}
