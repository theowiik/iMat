package Model;

import se.chalmers.cse.dat216.project.Product;

public interface AddProductObservable {
    void notifyAllObserversProductAdded(Product product, int i);
    void notifyAllObserversProductRemoved(Product product, int j);
    void addObserver(AddProductObserver observer);
    void removeAllObservers();
}
