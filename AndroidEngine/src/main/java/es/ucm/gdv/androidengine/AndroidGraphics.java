package es.ucm.gdv.androidengine;

import android.content.res.AssetManager;
import android.view.SurfaceView;

import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.RescaleGraphics;
import es.ucm.gdv.engine.Sprite;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.io.IOException;
import java.io.InputStream;



public class AndroidGraphics extends RescaleGraphics {

    SurfaceView _surfaceView;
    AssetManager _assetManager;
    InputStream _inputStream;
    SurfaceHolder _holder;
    Canvas _canvas = null;


    /**
     * Constructoda de la clase Android Graphics
     *
     * @param sv surfaceView
     * @param am AssetManager
     */
    public AndroidGraphics(SurfaceView sv, AssetManager am, int screenWidth, int screenHeight){
        super(screenWidth, screenHeight);
        _surfaceView = sv;
        _assetManager = am;
        _holder  = sv.getHolder();


    }

    /**
     * Carga una imagen y la devuelve, si falla lanza excepcion y devuelve null
     *
     * @param name Nombre de la ruta de la imagen
     * @return Image si funciona, null si falla
     */
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

    /**
     * Bloquea el canvas para ser usado posteriormente
     */
    public void lockCanvas(){
        _canvas = _holder.lockCanvas();
    }

    /**
     * Desbloquea el canvas, lo habitual es llamarlo tras renderizar
     */
    public void freeCanvas(){
        _holder.unlockCanvasAndPost(_canvas);
    }

    /**
     * Limpia la pantalla dibujando rellenando la aplicación con un color
     *
     * @param Color color de relleno
     */
    @Override
    public void clear(int Color) {
        _canvas.drawColor(Color);
    }

    /**
     * Recibe los parámetros ya escalados para pintar y los transorma a rectangulos
     * de android
     *
     * @param img imagen a pintar
     * @param dest Rectangulo destino de la imagen a pintar
     * @param src Rectangulo fuente donde se va a pintar
     */
    @Override
    protected void finalDrawImage(Image img, Rect dest, Rect src){
        android.graphics.Rect aDest = new android.graphics.Rect(
                dest.get_left(),dest.get_top(),dest.get_right(), dest.get_bottom());

        android.graphics.Rect aSrc = new android.graphics.Rect(
                src.get_left(), src.get_top(), src.get_right(), src.get_bottom());

        _canvas.drawBitmap(((AndroidImage)img).getBitmap(), aDest, aSrc, null);
    } //finalDrawImage


    /**
     * getter para conocer el holder
     *
     * @return SurfaceHolder
     */
    public SurfaceHolder get_holder() {
        return _holder;
    }
}
