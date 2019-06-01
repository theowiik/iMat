package Controller;

import Model.*;
import Model.Categories.Category;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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
        showAllProducts(true);
    }

    public void showAllProducts(boolean showFeature) {
        productBrowser.showAllProducts(showFeature);
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

    public void spawnShowAllProductsButton() {
        Button button = new Button();
        button.setText("Visa alla produkter");
        button.setLayoutX(30);
        button.setLayoutY(30);

        button.setOnAction(e -> {
            showAllProducts(false);
        });

        AnchorPane container = new AnchorPane();
        container.getChildren().add(button);
        container.setPadding(new Insets(30));
        productBrowser.addNode(container);
    }

    /**
     * Spawns a text on the card grid
     * @param text
     */
    public void spawnText(String text) {
        Text content = new Text();
        content.setText(text);
        content.setLayoutX(30);
        AnchorPane container = new AnchorPane();
        container.setPadding(new Insets(10));
        container.getChildren().add(content);
        productBrowser.addNode(container);
    }

    /**
     * Clears the box
     */
    public void clearCardVBox() {
        productBrowser.clearCardVBox();
    }

    @Override
    public void productAdded(Product product, int i) {
//        backendController.addToShoppingCart(product);
//        backendController.printShoppingCart();
//        System.out.println("+");
//        backendController.printShoppingCart();
    }

    @Override
    public void productRemoved(Product product, int i) {
//        backendController.removeFromShoppingCart(product);
//        backendController.printShoppingCart();
//        System.out.println("-");
    }
}
