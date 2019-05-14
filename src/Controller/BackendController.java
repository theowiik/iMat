package Controller;

import Model.CategoryCard;
import Model.ProductCard;
import Model.ProductCardFactory;
import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.*;

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
    private final List<Category> categories = new ArrayList<>();

    /**
     *
     */
    private BackendController() {
        db = IMatDataHandler.getInstance();
        populateProductCardMap();
        populateCategoryList();
    }

    private void populateCategoryList() {
        categories.add(Category.DAIRY);
        categories.add(Category.DRINKS);
        categories.add(Category.FRUITS);
    }

    public Map<String, ProductCard> getProductCardMap() {
        return productCardMap;
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
    private void populateProductCardMap() {
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

        for (ShoppingItem shoppingItem : db.getShoppingCart().getItems()) {
            if (shoppingItem.getProduct().getName().equals(product.getName())) {
                // There already is one of those in the cart.
                // Update.
                shoppingItem.setAmount(shoppingItem.getAmount() + 1);
                return;
            }
        }

        // New item
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

    /**
     *
     * @return
     */
    public List<CategoryCard> getCategoryCards() {
        List<CategoryCard> categoryCards = new ArrayList<>();

        for (Category category : categories) {
            CategoryCard categoryCard = new CategoryCard();
            categoryCards.add(categoryCard);
        }

        return categoryCards;
    }

    public void removeFromShoppingCart(Product product) {
        ShoppingCart cart = db.getShoppingCart();
        ShoppingItem item = new ShoppingItem(product);

        for (ShoppingItem shoppingItem : db.getShoppingCart().getItems()) {
            if (shoppingItem.getProduct().getName().equals(product.getName())) {
                // There already is one of those in the cart.
                // Update.
                shoppingItem.setAmount(shoppingItem.getAmount() - 1);

                if (shoppingItem.getAmount() <= 0) {
                    cart.removeItem(shoppingItem);
                }

                return;
            }
        }
    }

    public void printShoppingCart() {
        ShoppingCart shoppingCart = db.getShoppingCart();

        System.out.println("---------");

        for (ShoppingItem product : shoppingCart.getItems()) {
            System.out.println(product.getAmount() + " x " + product.getProduct().getName());
        }
    }

    public Product getProduct(int id) {
        return db.getProduct(id);
    }

    public List<Product> getProducts(int[] ids) {
        List<Product> products = new ArrayList<>();

        for (int i : ids) {
            products.add(getProduct(i));
        }

        return products;
    }

    public List<Product> getProducts(Category category) {
        int productIds[];
        switch (category) {
            case DAIRY:
                productIds = new int[]{1, 2, 3, 4, 6, 7, 8, 9, 10};
                break;
            case MEAT:
                productIds = new int[]{1, 2, 3, 4, 6, 7, 8, 9, 10};
                break;
            case NUTS:
                productIds = new int[]{1, 2, 3, 4, 6, 7, 8, 9, 10};
                break;
            case FRUITS:
                productIds = new int[]{1, 2, 3, 4, 6, 7, 8, 9, 10};
                break;
            case VEGETABLES:
                productIds = new int[]{1, 2, 3, 4, 6, 7, 8, 9, 10};
                break;
            case SWEETS:
                productIds = new int[]{1, 2, 3, 4, 6, 7, 8, 9, 10};
                break;
            case DRINKS:
                productIds = new int[]{1, 2, 3, 4, 6, 7, 8, 9, 10};
                break;
            default:
                productIds = new int[]{1, 2, 3, 4, 6, 7, 8, 9, 10};
        }

        final List<Product> products = getProducts(productIds);
        return products;
    }
    
    public List<Order> getReciepts() {
        return db.getOrders();
    }
}

