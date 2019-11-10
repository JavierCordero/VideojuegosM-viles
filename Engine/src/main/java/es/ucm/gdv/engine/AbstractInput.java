package es.ucm.gdv.engine;

import java.util.List;

public abstract class AbstractInput implements Input {

    protected List<TouchEvent> _touchEvents;

    @Override
    synchronized public List<TouchEvent> getTouchEvents(){
        List<TouchEvent> aux = _touchEvents;
        _touchEvents.clear();
        return  aux;
    }

    synchronized void addEvent(TouchEvent e){
        _touchEvents.add(e);
    }

}
