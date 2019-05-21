package Model;

import se.chalmers.cse.dat216.project.Product;

public interface AddProductObservable {
    void notifyAllObserversProductAdded(Product product);
    void notifyAllObserversProductRemoved(Product product);
    void addObserver(AddProductObserver observer);
    void removeAllObservers();
}
