package es.ucm.gdv.engine;

public class Rect {

    /**
     * Recibe 4 coordenadas, 2 para la posición del primer punto
     * y ortas 2 para el segundo punto. Con esos dos puntos construimos
     * El rectángulo.
     *
     * @param left Coordenada left
     * @param right Coordenada right
     * @param top Coordenada top del
     * @param bottom Coordenada bottom
     */
     public Rect(int left, int right, int top, int bottom){
         _left = left;
         _right = right;
         _top = top;
         _bottom = bottom;
     }

     int _left;
     int _right;
     int _top;
     int _bottom;

     public int get_left(){
         return _left;
     }
     public int get_right(){ return _right; }
     public int get_top(){
        return _top;
    }
     public int get_bottom(){
        return _bottom;
    }
}
