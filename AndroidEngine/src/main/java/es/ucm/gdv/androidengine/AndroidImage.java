package es.ucm.gdv.androidengine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

import es.ucm.gdv.engine.Image;

public class AndroidImage implements Image {

    int _width;
    int _height;
    Bitmap _sprite;

    public AndroidImage(InputStream inputStream) {
        _sprite = BitmapFactory.decodeStream(inputStream);
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
