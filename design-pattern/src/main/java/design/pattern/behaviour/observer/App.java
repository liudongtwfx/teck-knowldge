package design.pattern.behaviour.observer;

import java.util.Observable;
import java.util.Observer;

public class App {
    public static void main(String[] args) {
        Observer observer = (o, arg) -> System.out.println("hello world" + o.getClass() + arg);

        ChangedNotifier observable = new ChangedNotifier();
        observable.addObserver(observer);
        observable.setChanged();
        observable.notifyObservers();
    }


    private static class ChangedNotifier extends Observable {
        @Override
        public synchronized void setChanged() {
            super.setChanged();
        }
    }
}
