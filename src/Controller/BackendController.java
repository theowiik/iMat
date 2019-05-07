package Controller;

import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

/**
 * The controller of the backend.
 */
public class BackendController {
    private static IMatDataHandler db;

    /**
     *
     */
    public BackendController() {
        db = IMatDataHandler.getInstance();
    }

    public void addToShoppingCart(Product product) {
        ShoppingCart cart = db.getShoppingCart();
        ShoppingItem item = new ShoppingItem(product);
        cart.addItem(item);
    }

    /**
     * Returns a random product
     * @return Product
     */
    public Product getRandomProduct() {
        return db.getProduct(144);
    }
}
