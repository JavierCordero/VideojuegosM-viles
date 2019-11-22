package es.ucm.gdv.PCEngine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import es.ucm.gdv.engine.AbstractInput;
import es.ucm.gdv.engine.Input;


public class PCInput extends AbstractInput {

    PCGraphics.Ventana _ventana;

    public PCInput (PCGraphics.Ventana ventana){
        _ventana = ventana;
        MouseEvent me = new MouseEvent();

    }

    class MouseEvent implements MouseListener {

        public MouseEvent(){
            _ventana.addMouseListener(this);
        }


        @Override
        public void mousePressed(java.awt.event.MouseEvent mouseEvent) {
            TouchEvent t = new TouchEvent();
            t.set_event(EventType.TOUCH);
            t.set_x(mouseEvent.getX());
            t.set_y(mouseEvent.getY());
            t.set_fingerID(mouseEvent.getID());

            addEvent(t);
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent mouseEvent) {
            TouchEvent t = new TouchEvent();
            t.set_event(EventType.RELEASE);
            t.set_x(mouseEvent.getX());
            t.set_y(mouseEvent.getY());
            t.set_fingerID(mouseEvent.getID());

            addEvent(t);
        }

        //No son necesarios para la práctica
        @Override
        public void mouseClicked(java.awt.event.MouseEvent mouseEvent) { }

        @Override
        public void mouseEntered(java.awt.event.MouseEvent mouseEvent) { }

        @Override
        public void mouseExited(java.awt.event.MouseEvent mouseEvent) { }

    }
}
