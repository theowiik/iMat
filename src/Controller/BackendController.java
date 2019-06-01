package Controller;

import Model.*;
import Model.Categories.*;
import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.*;

import java.util.*;

/**
 * The controller of the backend.
 */
public class BackendController implements AddProductObserver {
    private static BackendController instance = null;
    public IMatDataHandler db;
    private final Map<String, ProductCard> productCardMap = new HashMap<String, ProductCard>();
//    private final Map<String, CategoryCard> categoryCardMap = new HashMap<String, CategoryCard>();
    private final List<CategoryName> availableCategories = new ArrayList<>();
    private final List<Category> categories = new ArrayList<>();

    /**
     *
     */
    private BackendController() {
        db = IMatDataHandler.getInstance();
        populateProductCardMap();
        //createSampleorders();
    }

    /*private void createSampleorders() {
        Product p = getProduct(9);
        addToShoppingCart(p);
        p = getProduct(12);
        addToShoppingCart(p);


        db.placeOrder(true);



    }*/

    public void initCategories() {
        availableCategories.add(CategoryName.DAIRY);
        categories.add(new Dairy());

        availableCategories.add(CategoryName.MEAT);
        categories.add(new Meat());
/*
        availableCategories.add(CategoryName.FRUITS);
        availableCategories.add(CategoryName.EXOTIC_FRUITS);
        availableCategories.add(CategoryName.CITRUS_FRUITS);
        availableCategories.add(CategoryName.BERRIES);
        availableCategories.add(CategoryName.MELONS);
        categories.add(new Fruits());

        availableCategories.add(CategoryName.FISH);
        availableCategories.add(CategoryName.SHELLFISH);
        categories.add(new Fish());

        availableCategories.add(CategoryName.DRINKS);
        availableCategories.add(CategoryName.COLD_DRINKS);
        availableCategories.add(CategoryName.HOT_DRINKS);
        categories.add(new Drinks());

        availableCategories.add(CategoryName.PASTRY);
        categories.add(new Pastry());

        availableCategories.add(CategoryName.BREAD);
        categories.add(new Bread());

        availableCategories.add(CategoryName.VEGETABLES);
        categories.add(new Vegetable());

        availableCategories.add(CategoryName.HERB);
        categories.add(new Herb());

        availableCategories.add(CategoryName.NUTS);
        categories.add(new Nuts());

        availableCategories.add(CategoryName.PANTRY);
        availableCategories.add(CategoryName.SEEDS);
        categories.add(new Pantry());

        availableCategories.add(CategoryName.SNACKS);
        categories.add(new Snacks());
*/
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

            double amount = getAmountInCart(product);
            if (amount != 0) {
                productCard.setAmount((int) amount);
                productCard.setAmountText(amount);
            }

            productCardMap.put(product.getName(), productCard);
        }
    }

    private boolean productIsInCart(Product product) {
        ShoppingCart shoppingCart = db.getShoppingCart();

        for (ShoppingItem shoppingItem : shoppingCart.getItems()) {
            String productSimpleName = product.getClass().getSimpleName();
            String cartSimpleName = shoppingItem.getProduct().getClass().getSimpleName();

            if (productSimpleName.equals(cartSimpleName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adds a product to the shopping cart.
     * @param product a product.
     */
    public void addToShoppingCart(Product product, int i) {
        ShoppingCart cart = db.getShoppingCart();

        for (ShoppingItem shoppingItem : db.getShoppingCart().getItems()) {
            if (shoppingItem.getProduct().getName().equals(product.getName())) {
                // There already is one of those in the cart.
                // Update.
                shoppingItem.setAmount(shoppingItem.getAmount() + i);

                // Update product card
                updateProductCardAmount(product);

                return;
            }
        }

        // New item
        ShoppingItem item = new ShoppingItem(product);
        cart.addItem(item);
    }

    public void updateProductCardAmount(Product product) {
        ProductCard productCard = getProductCard(product);
        int amountInCart = (int) getProductAmountInCart(product);
        productCard.setAmountText(amountInCart);
        productCard.setAmount(amountInCart);
//        productCard.setAmountText("hej");
//        System.out.println("ping..........................");
    }

    public ShoppingCart getShoppingCart() {
        ShoppingCart shoppingCart = db.getShoppingCart();
        return shoppingCart;
    }

    public double getProductAmountInCart(Product product) {
        ShoppingCart cart = db.getShoppingCart();
        double amountOfProd = 0;

        for (ShoppingItem shoppingItem : db.getShoppingCart().getItems()) {
            if(shoppingItem.getProduct().getName().equals(product.getName())) {
                amountOfProd = shoppingItem.getAmount();
            }
        }
        return amountOfProd;
    }


    public int getShoppingCartAmount() {
        return db.getShoppingCart().getItems().size();
    }

    public double getAmountInCart(Product product) {
        for (ShoppingItem shoppingItem : db.getShoppingCart().getItems()) {
            String productSimpleName = product.getName();
            String cartSimpleName = shoppingItem.getProduct().getName();

            if (productSimpleName.equals(cartSimpleName)) {
                return shoppingItem.getAmount();
            }
        }

        return 0;
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
        int startVal = 1;
        for (int i = startVal; i < (startVal + amount); i++) {
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

    public Customer getCustomer() {
        return db.getCustomer();
    }

    /**
     *
     * @return
     */
//    public List<CategoryCard> getCategoryCards() {
//        List<CategoryCard> categoryCards = new ArrayList<>();
//
//        for (CategoryName category : availableCategories) {
//            CategoryCard categoryCard = new CategoryCard(category);
//            categoryCards.add(categoryCard);
//        }
//
//        return categoryCards;
//    }

    public void removeFromShoppingCart(Product product, int i) {
        ShoppingCart cart = db.getShoppingCart();
        ShoppingItem item = new ShoppingItem(product);

        for (ShoppingItem shoppingItem : db.getShoppingCart().getItems()) {
            if (shoppingItem.getProduct().getName().equals(product.getName())) {
                // There already is one of those in the cart.
                // Update.
                shoppingItem.setAmount(shoppingItem.getAmount() - i);

                if (shoppingItem.getAmount() <= 0) {
                    cart.removeItem(shoppingItem);
                }

                updateProductCardAmount(product);

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

    public List<Product> getProducts(List<Integer> ids) {
        List<Product> products = new ArrayList<>();

        for (int i : ids) {
            products.add(getProduct(i));
        }

        return products;
    }

    public List<Product> getProducts(CategoryName category) {
        final List<Product> products = new ArrayList<>();
        for (Category currentCategory : categories) {
            if (currentCategory.getCategoryName().name().equals(category.name())) {
                products.addAll(currentCategory.getProducts());
            }
        }

        return products;
    }

    public int getTotalAmountOfItems() {
        int amount = 0;
        ShoppingCart shoppingCart = db.getShoppingCart();
        for (ShoppingItem product : shoppingCart.getItems()) {
            amount += product.getAmount();
        }
        return amount;
    }
    
    public List<Order> getReciepts() {
        return db.getOrders();
    }

    public List<Category> getcategories() {
        return new ArrayList<>(this.categories);
    }

    public List<ProductCard> getProductCards(List<Product> products) {
        List<ProductCard> productCards = new ArrayList<>();

        for (Product product : products) {
            productCards.add(getProductCard(product));
        }

        return productCards;
    }

    @Override
    public void productAdded(Product product, int i) {
        addToShoppingCart(product, i);
        for (ShoppingItem shoppingItem : db.getShoppingCart().getItems()) {
            db.getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
            break;
        }
    }

    @Override
    public void productRemoved(Product product, int i) {
        removeFromShoppingCart(product, i);
        for (ShoppingItem shoppingItem : db.getShoppingCart().getItems()) {
            db.getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
            break;
        }
    }

    
    public void placeOrder() {
        db.placeOrder(true);
    }

    public int getLastOrderNumber() {
        return db.getOrders().get(db.getOrders().size() - 1).getOrderNumber();
    }
}

