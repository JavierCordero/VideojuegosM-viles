package es.ucm.gdv.engine;

import java.util.List;

public interface Input {

    public enum EventType{ TOUCH, RELEASE, SLIDE };
    public List<TouchEvent> getTouchEvents();

    class TouchEvent{

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

        //SETTERS
        public void set_x(int x){ _x = x; }
        public void set_y(int y){ _y = y; }
        public void set_fingerID(int id){ _fingerID = id; }
        public void set_event(EventType event) { _event = event; }
    } //TouchEvent class
}//Input interface
