package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class CartItem extends AnchorPane implements CustomComponent {

    @FXML
    public Label cartItemName;

    @FXML
    public Button cartAddItem;

    @FXML
    public TextField cartAmountTxtField;

    @FXML
    public Button cartSubItem;

    public CartItem(Product product){
        setRoot();
        cartAmountTxtField = new TextField("0");
        this.cartItemName.setText(product.getName() + ":");
    }

    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cartItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void addCartItem() {
        System.out.println("hejj");
    }

    public void subCartItem() {

    }

    public void updateCost() {

    }

    public void updateAmount() {

    }
}