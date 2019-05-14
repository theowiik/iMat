package Controller;

import Model.ProductCard;
import Model.ProductCardFactory;
import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The controller of the backend.
 */
public class BackendController {
    private static BackendController instance = null;
    private static IMatDataHandler db;
    private final Map<String, ProductCard> productCardMap = new HashMap<String, ProductCard>();

    /**
     *
     */
    private BackendController() {
        db = IMatDataHandler.getInstance();
        populateProuctCardMap();
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
     * Adds all cards.
     */
    private void populateProuctCardMap() {
        List<Product> products = db.getProducts();
        for (Product product : products) {
            ProductCard productCard = ProductCardFactory.createProductCard(product, getProductImage(product));
            productCardMap.put(product.getName(), productCard);
        }
    }

    /**
     * Adds a product to the shopping cart.
     * @param product a product.
     */
    public void addToShoppingCart(Product product) {
        ShoppingCart cart = db.getShoppingCart();
        ShoppingItem item = new ShoppingItem(product);
        cart.addItem(item);
    }

    public int getShoppingCartAmount() {
        return db.getShoppingCart().getItems().size();
    }

    public double getTotal() {
        return db.getShoppingCart().getTotal();
    }

    /**
     * Returns a random product
     * @return Product
     */
    public Product getRandomProduct() {
        return db.getProduct(144);
    }

    /**
     * Returns a list of random products.
     * @return a list of random products.
     */
    public List<Product> getRandomProducts(int amount) {
        List<Product> products = new ArrayList<>();
        for (int i = 2; i < amount; i++) {
            products.add(db.getProduct(i));
        }
        return products;
    }

    /**
     * Returns the image of a product
     * @param product
     * @return
     */
    public Image getProductImage(Product product) {
        return db.getFXImage(product);
    }

    /**
     * Searches for products.
     * @param
     * @return
     */
    public List<Product> search(String input) {
        return db.findProducts(input);
    }

    /**
     * Returns the product card of a product.
     * @param product
     * @return
     */
    public ProductCard getProductCard(Product product) {
        return productCardMap.get(product.getName());

    }

    public String getFirstName() {
        return db.getCustomer().getFirstName();
    }
}
