package Model;

import Controller.BackendController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart1 extends AnchorPane implements CustomComponent {

    @FXML
    public AnchorPane cartItemArea;

    @FXML
    public Label totalCostLabel;

    @FXML
    public Button toCheckoutButton;

    public ShoppingCart1() {
        setRoot();
    }

    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shoppingCart.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
