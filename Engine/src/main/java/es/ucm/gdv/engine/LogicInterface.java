package es.ucm.gdv.engine;

public interface LogicInterface {
    public void init(Game game);
    public void update(float deltaTime);
    public Boolean render();
}
