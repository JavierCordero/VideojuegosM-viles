package es.ucm.gdv.PCLauncher;

import javax.swing.JFrame;

import es.ucm.gdv.PCEngine.PCGame;
import es.ucm.gdv.Logic.Logic;
import es.ucm.gdv.PCEngine.PCGraphics;
import es.ucm.gdv.engine.LogicInterface;

public class PCLaucher extends JFrame {

    public static void main(String[] arg){

        PCGame game = new PCGame(new Logic());

    }
}
