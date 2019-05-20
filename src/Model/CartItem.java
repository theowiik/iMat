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

    @FXML
    public Label cartAmount;

    @FXML
    public Label cartItem;

    public CartItem(Product product){
        setRoot();
        this.cartItemName.setText(product.getName());
        //TODO
    }

    /* TILLAGD FÖR TEST /Jesper
       public CartItem(String name){
        setRoot();
        this.cartItemName.setText(name);
    }
     */


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

}