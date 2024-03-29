package es.ucm.gdv.movilesp1;

import android.content.res.Configuration;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

import es.ucm.gdv.Logic.Logic;
import es.ucm.gdv.androidengine.AndroidGame;
import es.ucm.gdv.androidengine.AndroidGraphics;
import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.RescaleGraphics;


public class AndroidLauncher extends AppCompatActivity {

    AndroidGame game;
    Logic l;

    /**
     *  Se llama cuando la clase es creada para establecer todos los parámetros necesarios
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Logic logic = new Logic(); //Crea la lógica necesaria del juego
        Point size = getAndroidScreenSize();
        game = new AndroidGame(this, logic, size.x, size.y); //Crea el nuevo juego
        setContentView(game.getGameFlow());
        getSupportActionBar().hide();
    }

    @Override
    protected void onResume() {
        super.onResume();
        game.getGameFlow().resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        game.getGameFlow().pause();
    }

    /**
     * getter para conocer el tamaño de la pantalla
     *
     * @return(Point) tamaño de la pantalla
     */
    private Point getAndroidScreenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    /**
     * Método llamado cuando la configuaración de la pantalla del teléfono cambia.
     * Si el móvil se gira o es reescalado de alguna manera, esta función será llamada
     * y realizará los cálculos necesarios.
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Point p = getAndroidScreenSize();

        // CComprueba la orientación de la pantalla
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            game.ScreenOrientation(1920, 1080, p.x, p.y);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            game.ScreenOrientation(1080, 1920, p.x, p.y);
        }
    }
}
