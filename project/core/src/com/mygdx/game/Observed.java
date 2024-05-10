package com.mygdx.game;

public interface Observed {
    public void register(Observer o);
    public void unregister(Observer o);
    public void notifyObservers();
}
