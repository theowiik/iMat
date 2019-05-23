package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.util.ArrayList;


public class ShoppingCart1 extends AnchorPane implements CustomComponent, Subject {

    @FXML
    public FlowPane cartItemArea;

    @FXML
    public Label totalCostLabel;

    @FXML
    public ScrollPane scPane;

    private ArrayList<Observer> observers;
    public boolean isInFront = false;

    public ShoppingCart1() {
        observers = new ArrayList<Observer>();
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

    public void closeShoppingCart() {
        this.toBack();
        isInFront = !isInFront;
    }

    public void toCheckout(){
        notifyAllObservers();
        isInFront = !isInFront;
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregister(Observer o) {}


    @Override
    public void notifyAllObservers() {
        for (Observer o : observers) {
            o.FromShoppingCartToCheckout();
        }
    }
}
