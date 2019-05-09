package Controller;

import Model.ProductBrowser;
import Model.ProductCardFactory;
import Model.TitledSection;
import se.chalmers.cse.dat216.project.Product;

/**
 * The controller for the product browser view
 */
public class ProductBrowserController {
    private static BackendController backendController;
    private ProductBrowser productBrowser;
    private double productCardWidth;

    public ProductBrowserController() {
        this.backendController = BackendController.getInstance();
        this.productCardWidth = ProductCardFactory.createProductCard(backendController.getRandomProduct()).getWidth();
        this.productBrowser = new ProductBrowser();
        spawnSampleData();
        spawnSampleData();
        spawnSampleData();
        spawnSampleData();
        spawnSampleData();
        spawnSampleData();
        spawnSampleData();
        spawnSampleData();
        spawnSampleTitledSection();
        spawnSampleTitledSection();
        spawnSampleTitledSection();
        spawnSampleTitledSection();
        spawnSampleTitledSection();
        spawnSampleTitledSection();
        spawnSampleTitledSection();
        spawnSampleTitledSection();
        spawnSampleTitledSection();
        spawnSampleTitledSection();
        spawnSampleTitledSection();
        productBrowser.initTilePane();
    }

    /**
     *
     */
    private void spawnSampleTitledSection() {
        TitledSection titledSection = new TitledSection("Potatisar");
        productBrowser.spawnSampleTitledSection();
    }

    /**
     *
     */
    public void spawnSampleData() {
        Product product = backendController.getRandomProduct();
        productBrowser.spawnSampleData(product);
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
     */
    public void updatePrefColumns() {
        updatePrefColumns(300);
    }

    /**
     * Updates the amount of columns of products.
     * @param cardSize
     */
    public void updatePrefColumns(double cardSize) {
        double productContainerWidth = getProductContainerWidth();
        int cols = productBrowser.getRecommendedCols(cardSize);
        System.out.println("Current Width is: " + productContainerWidth);
        System.out.println("With a card size of: " + productCardWidth);
        System.out.println("It will fit " + cols + " columns.");
        productBrowser.setCols(cols);
    }
}
