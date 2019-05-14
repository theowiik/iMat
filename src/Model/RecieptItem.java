package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class RecieptItem extends AnchorPane implements CustomComponent {

    @FXML
    public Label date;

    @FXML
    public Label deliverer;

    @FXML
    public Label price;

    public RecieptItem(String date, String deliverer, double price) {
        setRoot();
        this.date.setText(date);
        this.deliverer.setText(deliverer);
        this.price.setText(String.valueOf(price));
    }

    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("recieptItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
