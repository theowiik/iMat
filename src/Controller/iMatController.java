package Controller;

import Model.ProductBrowser;
import Model.ProductCard;
import Model.ProductCardFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import se.chalmers.cse.dat216.project.Product;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The main controller for the application window.
 */
public class iMatController implements Initializable {
    private static BackendController backendController;
    private ProductBrowserController productBrowserController;

    @FXML
    public AnchorPane contentPane;
    @FXML
    public GridPane myGridPane;

    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backendController = BackendController.getInstance();
        productBrowserController = new ProductBrowserController();
        spawnProductBrowser();
        addListenerToContentPane();
    }

    /**
     * Adds a listener that listens to the width of the content pane.
     */
    public void addListenerToContentPane() {
        contentPane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                productBrowserController.updatePrefColumns();
                System.out.println("Window resized. Updating the amount of columns.");
            }
        });
    }

    /**
     * todo
     */
    public void appendToContentPane() {

    }

    /**
     * Shows the product browser
     */
    public void spawnProductBrowser() {
        ProductBrowser browser = productBrowserController.getProductBrowser();
        contentPane.getChildren().add(browser);
    }
}
