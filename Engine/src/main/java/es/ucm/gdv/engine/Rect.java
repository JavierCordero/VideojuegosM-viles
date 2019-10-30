package es.ucm.gdv.engine;

public class Rect {

    /**
     * Recibe 4 coordenadas, 2 para la posición del primer punto
     * y ortas 2 para el segundo punto. Con esos dos puntos construimos
     * El rectángulo.
     *
     * @param horzX Coordenada X del primer punto
     * @param horzY Coordenada Y del primer punto
     * @param vertX Coordenada X del segundo punto
     * @param vertY Coordenada Y del segundo punto
     */
     public Rect(int horzX, int horzY, int vertX, int vertY){
         _horzX = horzX;
         _horzY = horzY;
         _vertX = vertX;
         _vertY = vertY;
     }

     int _horzX;
     int _horzY;
     int _vertX;
     int _vertY;

     int get_horzX(){
         return _horzX;
     }
    int get_horzY(){
        return _horzY;
    }
    int get_vertX(){
        return _vertX;
    }
    int get_vertY(){
        return _vertY;
    }
}
