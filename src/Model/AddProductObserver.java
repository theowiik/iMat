package Model;

import se.chalmers.cse.dat216.project.Product;

public interface AddProductObserver {
    void productAdded(Product product, int i);
    void productRemoved(Product product, int j);
}
