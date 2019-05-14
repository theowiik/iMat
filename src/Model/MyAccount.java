package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Order;

import java.io.IOException;
import java.util.List;

public class MyAccount extends AnchorPane implements CustomComponent{

    @FXML
    public AnchorPane rightContainer;

    public void spawnRecieptView(List<Order> reciepts) {
        MyAccountReciept myAccountReciept = new MyAccountReciept(reciepts);
        rightContainer.getChildren().add(myAccountReciept);
    }

    public MyAccount(List<Order> reciepts) {
        setRoot();
        spawnRecieptView(reciepts);
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
