package Model;

import se.chalmers.cse.dat216.project.Product;

public interface ProductCardObserver {
    void productAdded(Product product);
    void productRemoved(Product product);
}
