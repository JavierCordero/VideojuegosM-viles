package es.ucm.gdv.movilesp1;

import android.content.res.Configuration;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

import es.ucm.gdv.Logic.Logic;
import es.ucm.gdv.androidengine.AndroidGame;
import es.ucm.gdv.engine.Game;


public class AndroidLauncher extends AppCompatActivity {

    AndroidGame game;
    Logic l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Logic logic = new Logic();
        Point size = getAndroidScreenSize();
        game = new AndroidGame(this, logic, size.x, size.y);
        setContentView(game.getGameFlow());
    }

    @Override
    protected void onResume(){
        super.onResume();
        game.getGameFlow().resume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        game.getGameFlow().pause();
    }

    private Point getAndroidScreenSize()
    {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }
}
