package Model;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import java.io.IOException;

public class ShoppingCart1 extends AnchorPane implements CustomComponent {

    @FXML
    public FlowPane cartItemArea;

    @FXML
    public Label totalCostLabel;

    @FXML
    public ScrollPane scPane;

    @FXML
    public Button toCheckoutButton;

    @FXML
    public Button closeButton;

    @FXML
    public AnchorPane closeShoppingCart;

    public boolean isInFront = false;

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

    public void mouseTrap(Event event){
        event.consume();
    }
}
