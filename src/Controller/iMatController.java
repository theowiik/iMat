package Controller;

import Model.ProductCard;
import Model.ProductCardFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import se.chalmers.cse.dat216.project.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class iMatController implements Initializable {
    private BackendController backendController = new BackendController();

    @FXML
    public TilePane productTilePane;

    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        spawnSampleData();
        System.out.println("heyop !");
    }

    /**
     *
     */
    public iMatController() {
        backendController = new BackendController();
    }

    /**
     * Spawns a basic card
     */
    public void spawnSampleData() {
        Product product = backendController.getDb().getProduct(144);
        ProductCard card = ProductCardFactory.createProductCard(product);

        productTilePane.getChildren().add(card);
    }
}
