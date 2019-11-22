package es.ucm.gdv.androidengine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

import es.ucm.gdv.engine.Image;

public class AndroidImage implements Image {

    Bitmap _bitmap;

    /**
     * Genera el bitmap necesario para poder representar la imagen en android.
     *
     */
    public AndroidImage(InputStream inputStream) {
        _bitmap = BitmapFactory.decodeStream(inputStream);
    }

    /**
     * getter para conocer el ancho de la imagen
     *
     * @return(int) ancho de la imagen
     */
    @Override
    public int getWidth() {
        return _bitmap.getWidth();
    }

    /**
     * getter para conocer el alto de la imagen
     *
     * @return(int) alto de la imagen
     */
    @Override
    public int getHeight() {
        return _bitmap.getHeight();
    }

    /**
     * getter para obtener la imagen
     *
     * @return(Bitmap) imagen
     */
    public Bitmap getBitmap(){ return _bitmap; }
}
