package Model;

import se.chalmers.cse.dat216.project.Product;

public interface ProductCardObservable {
    void notifyAllObserversProductAdded(Product product);
    void notifyAllObserversProductRemoved(Product product);
    void addObserver(ProductCardObserver observer);
    void removeAllObservers();
}
