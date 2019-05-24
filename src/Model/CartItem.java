package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartItem extends AnchorPane implements CustomComponent, AddProductObservable {

    @FXML
    public Label cartItemName;

    @FXML
    public Button cartAddItem;

    @FXML
    public TextField cartAmountTxtField;

    @FXML
    public Button cartSubItem;

    @FXML
    public Label cartPrice;

    private Product product;
    private ProductCard productCard;

    List<AddProductObserver> observers = new ArrayList<>();
    private double amount;

    public CartItem(Product product, double amountOfProd){
        setRoot();
        this.cartItemName.setText(product.getName() + ":");
        this.product = product;
        this.amount = amountOfProd;
        this.cartAmountTxtField.setText(((int)amountOfProd + " " + product.getUnitSuffix()));
        String s = String.format("%.2f", (product.getPrice() * amountOfProd));
        this.cartPrice.setText(s + " kr");
    }

    public Product getProduct() {
        return product;
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
        System.out.println("lägger till");
        notifyAllObserversProductAdded(product);
    }

    public void subCartItem() {
        System.out.println("tar bort");
        notifyAllObserversProductRemoved(product);
    }

    @Override
    public void notifyAllObserversProductAdded(Product product) {
        for (AddProductObserver addProductObserver : observers) {
            addProductObserver.productAdded(product);
            System.out.println("Clicked! (+)");
        }
    }

    @Override
    public void notifyAllObserversProductRemoved(Product product) {
        for (AddProductObserver addProductObserver : observers) {
            addProductObserver.productRemoved(product);
            System.out.println("Clicked! (-)");
        }
    }

    @Override
    public void addObserver(AddProductObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeAllObservers() {
    }
}