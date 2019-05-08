package Controller;

import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.util.List;

/**
 * The controller of the backend.
 */
public class BackendController {
    private static BackendController instance = null;
    private static IMatDataHandler db;

    /**
     *
     */
    private BackendController() {
        db = IMatDataHandler.getInstance();
    }

    /**
     * Returns the instance of the backend
     * @return
     */
    public static BackendController getInstance() {
        if (instance == null) {
            instance = new BackendController();
        }

        return instance;
    }

    /**
     * Adds a product to the shopping cart
     * @param product
     */
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

    /**
     * Returns the image of a product
     * @param product
     * @return
     */
    public Image getProductImage(Product product) {
        return db.getFXImage(product);
    }
}
