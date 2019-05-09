package Model;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MyAccount extends AnchorPane {
    public MyAccount() {
        setRoot();
    }

    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myAccountView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
