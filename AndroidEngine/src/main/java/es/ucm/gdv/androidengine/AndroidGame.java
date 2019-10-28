package es.ucm.gdv.androidengine;

import android.renderscript.ScriptGroup;

import java.util.Vector;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Input;

public class AndroidGame implements Game {

    Vector<AndroidImage> _sprites = new Vector<AndroidImage>(15, 5);
    Graphics _graphics;
    Input _input;

    public void AndroidGame(){
        _graphics = new AndroidGraphics();
        _input = new AndroidInput();
    }

    @Override
    public Graphics getGraphics() {
        return _graphics;
    }

    @Override
    public Input getInput() {
        return _input;
    }
}
