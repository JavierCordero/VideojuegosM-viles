package es.ucm.gdv.PCLauncher;

import javax.swing.JFrame;

import es.ucm.gdv.PCEngine.PCGame;
import es.ucm.gdv.Logic.Logic;

/**
 * PCLauncher
 *
 * Arranca el sistema para ordenadores
 */
public class PCLaucher extends JFrame {

    public static void main(String[] arg){

        int _width = 1080, _height = 720; // tamaños por defecto de la pantalla

        PCGame game = new PCGame(new Logic(), _width, _height);

    }
}
