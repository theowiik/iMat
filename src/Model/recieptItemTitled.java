package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

import java.io.IOException;

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


    public recieptItemTitled(String date, String lev, double price) {
        setRoot();
        this.date.setText(date);
        this.lev.setText(lev);
        this.price.setText(String.valueOf(price));

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
