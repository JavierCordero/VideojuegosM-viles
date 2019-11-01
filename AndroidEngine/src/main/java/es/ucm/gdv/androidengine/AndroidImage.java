package es.ucm.gdv.androidengine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

import es.ucm.gdv.engine.Image;

public class AndroidImage implements Image {

    Bitmap _bitmap;

    public AndroidImage(InputStream inputStream) {
        _bitmap = BitmapFactory.decodeStream(inputStream);
    }

    @Override
    public int getWidth() {
        return _bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return _bitmap.getHeight();
    }

    public Bitmap getBitmap(){ return _bitmap; }
}
