package es.ucm.gdv.PCEngine;

import es.ucm.gdv.engine.Image;

public class PCImage implements Image {

    int _width, _height;
    java.awt.Image _image;

    public PCImage(String name){
        name ="PCLauncher/src/main/assets/" + name;
        try {
            javax.imageio.ImageIO.read(new java.io.File(name));
        }
        catch (Exception e) {
            System.err.println(e + " PCImage unable to load the image with name " + "'" + name + "'");
        }
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
