package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javax.swing.text.html.ImageView;
import java.io.IOException;

public class Feature extends AnchorPane implements CustomComponent {

    @FXML
    public ImageView image;

    @FXML
    public Button getStartedButton;

    public Feature() {
        setRoot();
    }

    public void prominentDoneButtonPressed() {
        System.out.println("klikked");
    }

    public Button getGetStartedButton() {
        return getStartedButton;
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
