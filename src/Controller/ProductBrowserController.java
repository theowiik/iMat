package Controller;

import Model.*;
import Model.Categories.Category;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * The controller for the product browser view
 */
public class ProductBrowserController implements AddProductObserver {
    private static BackendController backendController;
    private ProductBrowser productBrowser;

    public ProductBrowserController() {
        this.backendController = BackendController.getInstance();
        List<Category> categories = new ArrayList<>();
        categories.addAll(backendController.getcategories());
        this.productBrowser = new ProductBrowser(categories);
        observeAllProductCards();
        showAllProducts();
    }

    public void showAllProducts() {
        productBrowser.showAllProducts();
    }

    /**
     * Spawns a sample card grid.
     */
    public void spawnProductCardGrid() {
        List<Product> products = backendController.getRandomProducts(10);
        spawnProductCardGrid(products);
    }

    /**
     * Spawns a card grid.
     * @param products
     */
    public void spawnProductCardGrid(List<Product> products) {
        List<AnchorPane> cards = new ArrayList<>();
        for (Product product : products) {
            cards.add(backendController.getProductCard(product));
        }
        productBrowser.spawnCardGrid(cards);
    }

    public void prominentDoneButtonPressed() {
        showAllProducts();
    }

//    public void spawnCategoryCardGrid() {
//        List<AnchorPane> cards = new ArrayList<>();
//        cards.addAll(backendController.getCategoryCards());
//        productBrowser.spawnCardGrid(cards);
//    }

//    public void spawnSubCategoryCardGrid(CategoryName category) {
//        List<AnchorPane> cards = new ArrayList<>();
//        cards.addAll(backendController.getSubCategoryCards(category));
//        productBrowser.spawnCardGrid(cards);
//    }

    private void observeAllProductCards() {
        for (ProductCard productCard : backendController.getProductCardMap().values()) {
            productCard.addObserver(this);
            productCard.addObserver(backendController);
        }
    }

    /**
     * Spawns a titles section
     */
    public void spawnTitledSection(String title) {
        productBrowser.spawnTitledSection(title);
    }

    /**
     *
     * @return
     */
    public ProductBrowser getProductBrowser() {
        return productBrowser;
    }

    /**
     *
     * @return
     */
    public double getProductContainerWidth() {
        return productBrowser.getProductContainerWidth();
    }

    /**
     * Updates the amount of columns of products.
     * @param cardSize
     */
    public void updatePrefColumns(double cardSize) {
        double availableWidth = productBrowser.getWidth() * 0.75;
        int cols = productBrowser.getRecommendedAmountOfColumns(cardSize, availableWidth);
        productBrowser.updateGaps(cols);
        System.out.println(cols);
    }

    /**
     * Clears the box
     */
    public void clearCardVBox() {
        productBrowser.clearCardVBox();
    }

    @Override
    public void productAdded(Product product) {
//        backendController.addToShoppingCart(product);
//        backendController.printShoppingCart();
//        System.out.println("+");
//        backendController.printShoppingCart();
    }

    @Override
    public void productRemoved(Product product) {
//        backendController.removeFromShoppingCart(product);
//        backendController.printShoppingCart();
//        System.out.println("-");
    }
}
