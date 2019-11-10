package es.ucm.gdv.engine;

import java.util.List;

public interface Input {

    public enum EventType{ TOUCH, RELEASE, SLIDE };
    public List<TouchEvent> getTouchEvents();

    class TouchEvent{
        //CONSTRUCTORA DE TOUCHEVENT
        public TouchEvent(EventType e, int x, int y, int id){
            _event = e;
            _x = x;
            _y = y;
            _fingerID = id;
        }

        EventType _event;

        int _x; //Coordenada x del evento
        int _y; //Coordenada y del evento

        int _fingerID; //identificador del dedo oboton pulsado

        //GETTERS
        public EventType getEvent() {
            return _event;
        }
        public int get_x() {
            return _x;
        }
        public int get_y() {
            return _y;
        }
        public int getFingerID() {
            return _fingerID;
        }
    } //TouchEvent class
}//Input interface
