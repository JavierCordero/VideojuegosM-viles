package es.ucm.gdv.androidengine;
import android.util.EventLog;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.List;
import es.ucm.gdv.engine.AbstractInput;

import es.ucm.gdv.engine.Input;

public class AndroidInput extends AbstractInput {

    AndroidInput(View view){

        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int action = motionEvent.getAction() & MotionEvent.ACTION_MASK;
                TouchEvent tE = new TouchEvent();
                tE.set_fingerID(motionEvent.getActionIndex());
                tE.set_x((int)motionEvent.getX(motionEvent.getActionIndex()));
                tE.set_y((int)motionEvent.getY(motionEvent.getActionIndex()));

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        tE.set_event(EventType.TOUCH);
                        break;

                    case MotionEvent.ACTION_UP:
                        tE.set_event(EventType.RELEASE);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        tE.set_event(EventType.SLIDE);
                        break;
                }
                addEvent(tE);
                return false;
            }
        });
    }
}
