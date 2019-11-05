package es.ucm.gdv.PCLauncher;

import javax.swing.JFrame;

import es.ucm.gdv.PCEngine.PCGame;
import es.ucm.gdv.Logic.Logic;
import es.ucm.gdv.PCEngine.PCGraphics;
import es.ucm.gdv.engine.LogicInterface;

public class PCLaucher extends JFrame {

    public static void main(String[] arg){

        int _width = 800, _height = 600;

        PCGame game = new PCGame(new Logic(), _width, _height);

    }
}
