package Model;

import Controller.iMatController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

/**
 * A "card" includes information about a product
 */
public class ProductCard extends AnchorPane implements CustomComponent {
    @FXML
    public Text name;
    @FXML
    public Text price;
    @FXML
    public Text unit;
    @FXML
    public ImageView image;
//    @FXML
//    public Label amount;
    private int amount = 0;

    /**
     * Creates a new product card.
     *
     * @param product          a product.
     */
    ProductCard(Product product, Image image) {
        setRoot();
        this.name.setText(product.getName());
        this.price.setText(String.valueOf(product.getPrice()));
        this.unit.setText(product.getUnit());
        this.image.setImage(image);
    }

    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void add() {
        amount++;
        System.out.println(amount);
    }

    public void subtract() {
        amount--;
        System.out.println(amount);
    }

    public int getAmount() {
        return amount;
    }
}
