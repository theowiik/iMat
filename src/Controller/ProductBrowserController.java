package Controller;

import Model.ProductCard;
import Model.ProductCardFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Product;
import sun.plugin.javascript.navig.Anchor;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductBrowserController {
    private static BackendController backendController;

    @FXML
    public AnchorPane productBrowserRootPane;
    @FXML
    public TilePane productTilePane;

    public ProductBrowserController() {
        this.backendController = BackendController.getInstance();
        System.out.println("IN PRODUCT BROWSE INIT");
    }

    /**
     * Configs the tile pane containing products and categories.
     */
    public void initTilePane() {
        productTilePane.setHgap(10);
        productTilePane.setVgap(10);
    }

    /**
     * Spawns a basic card
     */
    public void spawnSampleData() {
        Product product = backendController.getRandomProduct();
        ProductCard card = ProductCardFactory.createProductCard(product);
        productTilePane.getChildren().add(card);
    }
}
