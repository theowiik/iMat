package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

import java.io.IOException;

public class listItemTitled extends TitledPane implements CustomComponent {

    @FXML
    public Label listName;

    @FXML
    public Label listCategory;

    @FXML
    public Label listPrice;

    /*
    @FXML
    public AnchorPane content;
     */


    public listItemTitled(String name, String category, double price) {
        setRoot();
        this.listName.setText(name);
        this.listCategory.setText(category);
        this.listPrice.setText(String.valueOf(price));

    }

    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listItemTitled.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
