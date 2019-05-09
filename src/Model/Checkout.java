package Model;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Checkout extends AnchorPane {
    public Checkout() {
        setRoot();
    }

    private void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("checkoutView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
