package es.ucm.gdv.movilesp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import es.ucm.gdv.androidengine.AndroidGame;


public class AndroidLauncher extends AppCompatActivity {

    AndroidGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Hola Mundo!");
        game = new AndroidGame(this);
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
