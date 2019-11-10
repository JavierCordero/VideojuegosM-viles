package es.ucm.gdv.androidengine;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.List;
import es.ucm.gdv.engine.AbstractInput;

import es.ucm.gdv.engine.Input;

public class AndroidInput extends AbstractInput implements View.OnTouchListener {

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        int action = motionEvent.getAction() & MotionEvent.ACTION_MASK;
        TouchEvent tE;

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                tE  = new TouchEvent(EventType.TOUCH,
                        (int)motionEvent.getX(motionEvent.getActionIndex()),
                        (int)motionEvent.getY(motionEvent.getActionIndex()),
                        motionEvent.getActionIndex());
                    break;
            case MotionEvent.ACTION_UP:
                tE = new TouchEvent(EventType.RELEASE,
                        (int)motionEvent.getX(motionEvent.getActionIndex()),
                        (int)motionEvent.getY(motionEvent.getActionIndex()),
                        motionEvent.getActionIndex());
                    break ;

            case MotionEvent.ACTION_MOVE:
                    break;
        }

        return false;
    }
}
