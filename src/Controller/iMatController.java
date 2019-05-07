package Controller;

import Model.ProductBrowser;
import Model.ProductCard;
import Model.ProductCardFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import se.chalmers.cse.dat216.project.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class iMatController implements Initializable {
    private static BackendController backendController;
    private ProductBrowserController productBrowserController;

    @FXML
    public AnchorPane contentPane;

    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Init for iMatController");
        backendController = BackendController.getInstance();
        productBrowserController = new ProductBrowserController();
        spawnProductBrowser();
        System.out.println("iMatController initialized");
    }

    /**
     * Shows the product browser
     */
    public void spawnProductBrowser() {
        ProductBrowser browser = new ProductBrowser();
        contentPane.getChildren().add(browser);
    }
}
