package Model;

import Controller.iMatController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Product;

import javax.swing.text.html.ImageView;
import java.io.IOException;

/**
 * A "card" includes information about a product
 */
public class ProductCard extends AnchorPane {
    private iMatController parentController;

    @FXML
    public Text name;
    @FXML
    public Text price;
    @FXML
    public Text unit;
//    @FXML
//    public ImageView image;
//    @FXML
//    public Label amount;

    /**
     * Creates a new product card.
     *
     * @param product          a product.
     * @param parentController a iMatController.
     */
    ProductCard(Product product, iMatController parentController) {
        // FXML init
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        // Instances
        this.parentController = parentController;

        this.name.setText(product.getName());
        this.price.setText(String.valueOf(product.getPrice()));
        this.unit.setText(product.getUnit());

    }
}
