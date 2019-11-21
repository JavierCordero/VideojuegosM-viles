package es.ucm.gdv.engine;

import java.util.List;

public interface LogicInterface {
    public void init(Game game);
    public void handleEvent(List<Input.TouchEvent> l);
    public void update(float deltaTime);
    public Boolean render();
}
