package es.ucm.gdv.Logic;

public class Sin {
    int sinFunct;
    int sinSpeed = 5;

    public int updateSin(){
        sinFunct += sinSpeed;
        if(sinFunct >= 360) sinFunct = 0;
        return (int)(Math.sin(Math.toRadians(sinFunct)) * 10);
    }

}
