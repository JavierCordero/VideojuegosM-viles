package es.ucm.gdv.engine;

public abstract class State implements LogicInterface {

    @Override
    public abstract void init(Game game);
    @Override
    public abstract void update(float deltaTime);
    @Override
    public abstract Boolean render();

    public String stateName;
}
