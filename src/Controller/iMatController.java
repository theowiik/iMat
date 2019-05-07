package Controller;

import Model.ProductCard;
import Model.ProductCardFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import se.chalmers.cse.dat216.project.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class iMatController implements Initializable {
    private BackendController backendController;

    @FXML
    public TilePane productTilePane;

    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTilePane();
        spawnSampleData();
        spawnSampleData();
        spawnSampleData();
        spawnSampleData();
        spawnSampleData();
        System.out.println("heyop !");
    }

    /**
     * Configs the tile pane containing products and categories.
     */
    public void initTilePane() {
        productTilePane.setHgap(10);
        productTilePane.setVgap(10);
    }

    /**
     * Constructor
     */
    public iMatController() {
        backendController = new BackendController();
    }

    /**
     * Spawns a basic card
     */
    public void spawnSampleData() {
        Product product = backendController.getRandomProduct();
        ProductCard card = ProductCardFactory.createProductCard(product, this);
        productTilePane.getChildren().add(card);
    }
}
