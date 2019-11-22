package es.ucm.gdv.PCEngine;

import es.ucm.gdv.engine.Image;

public class PCImage implements Image {

    int _width, _height;
    java.awt.Image _image;

    public PCImage(String name){
        name ="assets/" + name; //Ruta principal para encontrar los assets
        try {
            _image = javax.imageio.ImageIO.read(new java.io.File(name));
            _width = _image.getWidth(null);
            _height = _image.getHeight(null);
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

    public java.awt.Image get_image(){
        return _image;
    }
}
