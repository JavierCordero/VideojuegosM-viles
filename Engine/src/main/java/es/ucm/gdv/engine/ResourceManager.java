package es.ucm.gdv.engine;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager {

    Map<String, Sprite> _sprites;
    Map<String, Image> _images;
    Game _g;

    /**
     * Crea la instancia de la clase
     * @param g Game
     */
    public ResourceManager(Game g){
        _g = g;
        _sprites = new HashMap<String, Sprite>();
        _images = new HashMap<String, Image>();
    }

    /**
     * Almacena la imagen previamente cargada con un identificador
     *
     * @param str identificador de la imagen
     * @param i imagen a almacenar previamente cargada
     */
    public void LoadImage(String str, Image i) {
        _images.put(str, i);
    }

    /**
     * Crea una sprite usando la imagen con el nombre indicado. La imagen debe estar cargada
     * Previamente con el metodo LoadImage y debe usarse el nombre exacto de la imagen cargada
     *
     * @param name nombre de la imagen cargada previamente
     * @param src Rectangulo de la imagen que ocupara el sprite
     * @param SpriteName nombre con el que se guardara la referencia
     */
    public void createSpriteFromImage(String name, Rect src, String SpriteName, int alpha){
        _sprites.put(SpriteName, new Sprite(_images.get(name), src, alpha));
    }
    public void createSpriteFromImage(String name, Rect src, int x, int y, String SpriteName, int alpha){
        _sprites.put(SpriteName, new Sprite(_images.get(name), src,x,y,alpha));
    }

    /**
     * Devuelve una imagen
     *
     * @param str identificador de la imagen que se quiere recibir
     * @return Image
     */
    public Image getImage(String str){ return _images.get(str); }

    /**
     * Devuelve un sprite
     *
     * @param str identificador del sprite que se quiere recibir
     * @return Sprite
     */
    public Sprite getSprite(String str){ return _sprites.get(str); }
}
