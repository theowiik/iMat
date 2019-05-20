package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import sun.plugin.javascript.navig.Anchor;

import javax.swing.text.html.ImageView;
import java.io.IOException;

public class Feature extends AnchorPane implements CustomComponent {

    @FXML
    public ImageView image;

    public Feature() {
        setRoot();
    }

    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("feature.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
