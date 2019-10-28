package es.ucm.gdv.androidengine;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.view.SurfaceView;

import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;



public class AndroidGraphics implements Graphics{

    private final int windowWidth = 1080;
    private final int windowHeight = 1920;

    SurfaceView _surfaceView;
    AssetManager _assetManager;
    InputStream _inputStream;

    public void AndroidGraphics(SurfaceView sv, AssetManager am){
        _surfaceView = sv;
        _assetManager = am;
    }

    @Override
    public Image newImage(String name) {
        try{
            _inputStream = _assetManager.open(name);
            AndroidImage i = new AndroidImage(_inputStream);
            return i;
        } catch (IOException e) {
            android.util.Log.e("AndroidGraphics", "Error al cargar imagen");
        }
        finally {
            try{
                _inputStream.close();
            }catch (IOException e) {
                android.util.Log.e("AndroidGraphics",
                        "Error al cerrar el flujo de entrada");
            }
        }
        return null;
    }

    @Override
    public void clear(int Color) {

    }

    @Override
    public void drawImage(Image image) {

    }

    @Override
    public int getWidth() {
        return windowWidth;
    }

    @Override
    public int getHeight() {
        return windowHeight;
    }
}
