package es.ucm.gdv.androidengine;

import android.content.res.AssetManager;
import android.view.SurfaceView;

import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.io.IOException;
import java.io.InputStream;



public class AndroidGraphics implements Graphics{

    SurfaceView _surfaceView;
    AssetManager _assetManager;
    InputStream _inputStream;
    SurfaceHolder _holder;

    public AndroidGraphics(SurfaceView sv, AssetManager am){
        _surfaceView = sv;
        _assetManager = am;
        _holder  = sv.getHolder();
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
        return _surfaceView.getWidth();
    }

    @Override
    public int getHeight() {
        return _surfaceView.getHeight();
    }

    private Canvas getCanvas(){
        Canvas _canvas;
        while(!_holder.getSurface().isValid());{
           _canvas = _holder.lockCanvas();
        }
        return _canvas;
    }
    private void freeCanvas(Canvas c){
        _holder.unlockCanvasAndPost(c);
    }

    public void renderPresent(){
        Canvas canvas = getCanvas();

        //Render

        freeCanvas(canvas);
    }
}
