package Controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductCardController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("hi, my name is " + this.getClass().getSimpleName());
    }
}
