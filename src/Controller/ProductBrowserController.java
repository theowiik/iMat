package Controller;

import Model.ProductBrowser;
import Model.ProductCard;
import Model.ProductCardFactory;
import Model.TitledSection;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * The controller for the product browser view
 */
public class ProductBrowserController {
    private static BackendController backendController;
    private ProductBrowser productBrowser;

    public ProductBrowserController() {
        this.backendController = BackendController.getInstance();
        this.productBrowser = new ProductBrowser();
        spawnTitledSection("Category 1");
        spawnCardGrid();
        spawnTitledSection("Category 2");
        spawnCardGrid();
    }

    /**
     * Spawns a sample card grid.
     */
    public void spawnCardGrid() {
        List<Product> products = backendController.getRandomProducts(10);
        spawnCardGrid(products);
    }

    /**
     * Spawns a card grid.
     * @param products
     */
    public void spawnCardGrid(List<Product> products) {
        List<AnchorPane> cards = new ArrayList<>();
        for (Product product : products) {
            cards.add(backendController.getProductCard(product));
        }
        productBrowser.spawnCardGrid(cards);
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
}
