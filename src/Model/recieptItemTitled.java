package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class recieptItemTitled extends TitledPane implements CustomComponent, RecieptObservable{

    @FXML
    public Label date;

    @FXML
    public Label lev;

    @FXML
    public Label price;

    /*
    @FXML
    public AnchorPane content;
     */

    @FXML
    public FlowPane recieptContainer;

    public List<ShoppingItem> products = new ArrayList<>();

    MyAccountShoppingList msl;

    private ArrayList<RecieptObserver> observers = new ArrayList<>();

    public recieptItemTitled(String date, String lev, double price) {
        setRoot();
        this.date.setText(date);
        this.lev.setText(lev);
        this.price.setText(String.valueOf(price));


    }



    public void spawncartItems() {
        for (ShoppingItem p : products) {
            CartItem cartItem = new CartItem(p.getProduct(), p.getAmount());
            cartItem.cartAddItem.setVisible(false);
            cartItem.cartSubItem.setVisible(false);
            cartItem.cartAmountTxtField.setText(String.valueOf(p.getAmount()));
            cartItem.cartAmountTxtField.setEditable(false);

            recieptContainer.getChildren().add(cartItem);
        }
    }

    public void saveAsShoppingList(){
        System.out.println("hellooo");
        notifyNewShoppingList();
    }

    public void addProduct(ShoppingItem product) {
        this.products.add(product);
    }

    public void addProductsList(List<ShoppingItem> shoppingitems) {
        this.products = shoppingitems;
    }

    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("recieptItemTitled.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    @Override
    public void addListener(RecieptObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyNewShoppingList() {
        for (RecieptObserver o : observers)
            o.ShoppingListAdded(products);
    }
}
