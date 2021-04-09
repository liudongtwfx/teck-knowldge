package design.pattern.behaviour.observer;

import java.util.Observable;

public interface Observer {
    void update(Observable o, Object arg);
}
