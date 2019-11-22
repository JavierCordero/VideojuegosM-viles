package es.ucm.gdv.engine;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractInput implements Input {

    protected List<TouchEvent> _touchEvents = new ArrayList<TouchEvent>();

    @Override
    synchronized public List<TouchEvent> getTouchEvents(){
        List<TouchEvent> aux = new ArrayList<>(_touchEvents);
        _touchEvents.clear();
        return  aux;
    }


    synchronized protected void addEvent(TouchEvent e){_touchEvents.add(e);}

}
