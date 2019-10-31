package es.ucm.gdv.movilesp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        Logic l = new Logic();
        game = new AndroidGame(this, l);
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


}
