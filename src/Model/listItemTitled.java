package Model;

import Controller.BackendController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class listItemTitled extends TitledPane implements CustomComponent {

    @FXML
    public Label listName;

    @FXML
    public Label listCategory;

    @FXML
    public Label listPrice;

    @FXML
    public FlowPane cartItemContainer;

    @FXML
    public Button addToCartButton;

    public ArrayList<ShoppingItem> products = new ArrayList<>();

    public listItemTitled(String name, String category, double price) {
        setRoot();
        this.listName.setText(name);
        this.listCategory.setText(category);
        this.listPrice.setText(String.valueOf(price));
        spawncartItems();

    }

    public void spawncartItems() {
        for (ShoppingItem p : products) {
            CartItem cartItem = new CartItem(p.getProduct(), p.getAmount());
            cartItem.cartAmountTxtField.setText(String.valueOf(p.getAmount()));
            cartItemContainer.getChildren().add(cartItem);
        }
    }

    public void addProduct(ShoppingItem product) {
        this.products.add(product);
    }

    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listItemTitled.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void addToCart() {
        for (ShoppingItem p : products)
            BackendController.getInstance().addToShoppingCart(p.getProduct());
            BackendController.getInstance().printShoppingCart();
    }

    public void setPrice(double d) {
        listPrice.setText(String.valueOf(d));
    }
}
