package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MyAccount extends AnchorPane implements CustomComponent{

    @FXML
    public AnchorPane rightContainer;

    public void spawnRecieptView() {
        MyAccountReciept myAccountReciept = new MyAccountReciept();
        rightContainer.getChildren().add(myAccountReciept);
    }

    public MyAccount() {
        setRoot();
        spawnRecieptView();
    }

    @Override
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
