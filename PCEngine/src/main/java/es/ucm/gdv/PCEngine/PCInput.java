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

    class MouseEvent implements MouseListener, MouseMotionListener {

        public MouseEvent(){
            _ventana.addMouseListener(this);
            _ventana.addMouseMotionListener(this);
        }

        @Override
        public void mouseClicked(java.awt.event.MouseEvent mouseEvent) {

            TouchEvent t = new TouchEvent(EventType.TOUCH, mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getID());
            TouchEvent r = new TouchEvent(EventType.RELEASE, mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getID());
            addEvent(t);
            addEvent(r);
        }

        @Override
        public void mousePressed(java.awt.event.MouseEvent mouseEvent) {
            TouchEvent t = new TouchEvent(EventType.TOUCH, mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getID());
            addEvent(t);
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent mouseEvent) {
            TouchEvent t = new TouchEvent(EventType.RELEASE, mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getID());
            addEvent(t);
        }

        @Override
        public void mouseEntered(java.awt.event.MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent mouseEvent) {

        }

        @Override
        public void mouseDragged(java.awt.event.MouseEvent mouseEvent) {

        }

        @Override
        public void mouseMoved(java.awt.event.MouseEvent mouseEvent) {

        }
    }
}
