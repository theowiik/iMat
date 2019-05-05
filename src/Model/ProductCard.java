package Model;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Product;

/**
 * A "card" including information about a product
 */
public class ProductCard extends AnchorPane {
    @FXML
    public Text name;
    @FXML
    public Text price;
    @FXML
    public Text unit;

    ProductCard(Product product) {
        this.name.setText(product.getName());
        this.price.setText(String.valueOf(product.getPrice()));
        this.unit.setText(product.getUnit());
    }

    ProductCard(String name, String price, String unit) {
        this.name.setText(name);
        this.price.setText(price);
        this.unit.setText(unit);
    }
}
