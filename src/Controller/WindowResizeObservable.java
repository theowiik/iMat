package Controller;

public interface WindowResizeObservable {
    void addObserver(WindowResizeObserver observer);
    void notifyAllObservers();
}
