package es.ucm.gdv.Logic;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Input;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.ResourceManager;
import es.ucm.gdv.engine.Sprite;
import es.ucm.gdv.engine.State;
import es.ucm.gdv.engine.StatesManager;


/**
 * Estado principal del juego, dónde se ejecutará toda la lógica de las bolas, las palas...
 */
public class playState extends State {

    Game _game;
    ResourceManager _rM;
    StatesManager _statesManager;

    String actualPlayer = "whitePlayer";

    //parametros de los numeros
    int numbersHeight = 400;
    int numbersSeparation = 50;
    int rigthBorderNumberSeparation = 20;

    particleSystem pSystem;
    ballManager bManager;
    Graphics _G;

    Arrows _arrows;
    Logic.BehindColor _bColor;
    int _myColor;
    int numSprites = 0;
    Sprite numbers [] = new Sprite [10];
    flashEffect _flashEffect;

    Sprite backArrow;
    Sprite backArrow2;

    public playState(StatesManager statesManager, ResourceManager resourceManager,
                     Arrows arrow, Logic.BehindColor bColor){
        _statesManager = statesManager;
        _rM = resourceManager;
        _arrows = arrow;
        _bColor = bColor;
    }

    @Override
    public void init(Game game) {
        //PARAMETROS INICIALES
        _game = game;
        _G = _game.getGraphics();

        pSystem = new particleSystem(_rM, _G);
        bManager = new ballManager(_rM, _G, pSystem, _statesManager);
        bManager.setActualPlayer(actualPlayer);

        backArrow = _rM.getSprite("BGArrow1");
        backArrow2 = _rM.getSprite("BGArrow2");

        //Creamos todos los numeros necesarios para la puntiación (max 999)
        for( int i = 0; i < 10; i++){
            numbers[i] =_rM.getSprite("number" + i);
        }

        //Seleccionamos de manera aleatoria el color de juego
        _myColor = (int) (Math.random() * _bColor.getBGcolors().length-1) + 1;

        //Generamos el efecto de flash y lo inicializamos
        _flashEffect = new flashEffect();
        _flashEffect.init(_rM, _G);

        _bColor.setCurrentColor(_myColor);
    }

    @Override
    public void handleEvent(Input.TouchEvent event){
            if(event.getEvent() == Input.EventType.TOUCH)
                cambiaPlayer(); //Cambia entre los dos jugadores disponibles
        }

    @Override
    public void update(float deltaTime) {
        _arrows.draw(deltaTime);

        bManager.actualizaBola(deltaTime);

        pSystem.ActualizaPartuculas(deltaTime);

        _flashEffect.changeAlpha();
    }

    @Override
    public Boolean render() {

        Rect bacArrowRect = backArrow.get_destRect();
        _rM.getSprite(_bColor.getBGcolors()[_bColor.currentColor]).draw(_G, new Rect(bacArrowRect.get_left(),
                bacArrowRect.get_right(),
                0,
                _G.getHeight()));

        backArrow.draw(_G, backArrow.get_destRect());
        backArrow2.draw(_G, backArrow2.get_destRect());

        bManager.renderBalls();
        pSystem.renderPartuculas();

        Sprite player = _rM.getSprite(actualPlayer);

        player.draw(_G,player.get_destRect());

        drawNumber();

        _flashEffect.draw(); //Efecto para dibujar el flash al entrar en cada estado

        return true;
    }

    //Pinta los números en sus lugares correspondientes dependiendo de la puntuación actual
    void drawNumber(){

        int points = bManager.getPoints();
        int residuo;
        int centenas = (points/100);
        residuo = points%100;
        int decenas = (residuo/10);
        residuo = residuo%10;
        int unidades = (residuo/1);

        Rect unidadesRect = new Rect(_G.getWidth()-numbers[unidades].getSpriteWidth()-rigthBorderNumberSeparation,
                                    _G.getWidth()-rigthBorderNumberSeparation,
                                    numbersHeight,
                                    numbersHeight+numbers[unidades].getSpriteHeight());

        Rect decenasRect = new Rect(unidadesRect.get_left()-numbers[decenas].getSpriteWidth() + numbersSeparation,
                                    unidadesRect.get_left()-1 + numbersSeparation,
                                    numbersHeight,
                                    numbersHeight+numbers[decenas].getSpriteHeight());

        Rect centenasRect = new Rect(decenasRect.get_left()-numbers[centenas].getSpriteWidth() + numbersSeparation,
                                decenasRect.get_left()-1 + numbersSeparation,
                             numbersHeight,
                            numbersHeight+numbers[centenas].getSpriteHeight());

        numbers[unidades].draw(_G, unidadesRect);
        if(points > 9)numbers[decenas].draw(_G, decenasRect);
        if(points > 99)numbers[centenas].draw(_G, centenasRect);
    }

    //Cambia la imagen del jugador
    void cambiaPlayer(){
        if(actualPlayer == "whitePlayer") actualPlayer = "blackPlayer";
        else actualPlayer = "whitePlayer";

        bManager.setActualPlayer(actualPlayer);
    }

}
