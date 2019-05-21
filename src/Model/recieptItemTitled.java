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

public class recieptItemTitled extends TitledPane implements CustomComponent {

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

    public ArrayList<ShoppingItem> products = new ArrayList<>();

    MyAccountShoppingList msl;

    public recieptItemTitled(String date, String lev, double price) {
        setRoot();
        this.date.setText(date);
        this.lev.setText(lev);
        this.price.setText(String.valueOf(price));

    }



    public void spawncartItems() {
        for (ShoppingItem p : products) {
            CartItem cartItem = new CartItem(p.getProduct());
            cartItem.cartAddItem.setVisible(false);
            cartItem.cartSubItem.setVisible(false);
            cartItem.cartAmountTxtField.setText(String.valueOf(p.getAmount()));
            cartItem.cartAmountTxtField.setEditable(false);

            recieptContainer.getChildren().add(cartItem);
        }
    }

    public void saveAsShoppingList(){
        listItemTitled lit = new listItemTitled("Det som " + lev.getText() + " levererade." , "Övrigt", 123);
        msl.newList(lit,products);
    }

    public void addProduct(ShoppingItem product) {
        this.products.add(product);
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


}
