package es.ucm.gdv.androidengine;

import android.content.res.AssetManager;
import android.view.SurfaceView;

import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

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

    /**
     * Dibuja una imagen completa en pantalla en coordenadas x, y
     *
     * @param image Imagen donde se encuentra el sprite a dibujar
     * @param x coordenada x donde se dibujara el primer pixel
     * @param y coordenada y donde se dibujara el primer pixel
     * @param alpha transparencia del sprite
     */
    @Override
    public void drawImage(Image image, int x, int y, int alpha) {

    }

    /**
     * Dibuja la fracción seleccionada de imagen en pantalla en coordenadas x, y
     *
     * @param image Imagen donde se encuentra el sprite a dibujar
     * @param src rectángulo de la imagen a dibujar (sprite)
     * @param x coordenada x donde se dibujara el primer pixel
     * @param y coordenada y donde se dibujara el primer pixel
     * @param alpha transparencia del sprite
     */
    @Override
    public void drawImage(Image image, Rect src, int x, int y, int alpha) {

    }

    /**
     * Dibuja una fracción de imagen en pantalla la posición y tamaño del rectangulo dest
     *
     * @param image Imagen donde se encuentra el sprite a dibujar
     * @param src rectángulo de la imagen a dibujar (sprite)
     * @param dest rectangulo destino de dibujo, afecta escalado
     * @param alpha transparencia del sprite
     */
    @Override
    public void drawImage(Image image, Rect src, Rect dest, int alpha) {

    }

    /**
     * getter para conocer dimensiones de pantalla
     *
     * @return int en pixeles del ancho de pantalla
     */
    @Override
    public int getWidth() {
        return _surfaceView.getWidth();
    }

    /**
     * getter para conocer dimensiones de pantalla
     *
     * @return int en pixeles del alto de pantalla
     */
    @Override
    public int getHeight() {
        return _surfaceView.getHeight();
    }

}
