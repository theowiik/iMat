package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class ProductBrowser extends AnchorPane {
    @FXML
    public Text testText;

    public ProductBrowser() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productBrowserView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        System.out.println("Initialized the product browser");

        testText.setText("hey");
    }
}
