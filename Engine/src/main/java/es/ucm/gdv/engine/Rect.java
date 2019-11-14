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
     public Rect(float left, float right, float top, float bottom){
         _left = left;
         _right = right;
         _top = top;
         _bottom = bottom;
     }

    float _left;
    float _right;
    float _top;
    float _bottom;

     public float get_left(){
         return _left;
     }
     public float get_right(){ return _right; }
     public float get_top(){
        return _top;
    }
     public float get_bottom(){
        return _bottom;
    }

    public void set_left(int _left) {
        this._left = _left;
    }

    public void set_right(int _right) {
        this._right = _right;
    }

    public void set_top(int _top) {
        this._top = _top;
    }

    public void set_bottom(int _bottom) {
        this._bottom = _bottom;
    }

    public void setPosition(float left, float right, float top, float bottom){
        _left = left;
        _right = right;
        _top = top;
        _bottom = bottom;
    }

}
