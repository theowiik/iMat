package Model;

import Controller.CategoryName;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class Breadcrumb extends AnchorPane implements CustomComponent {

    @FXML
    public Text pathText;
    @FXML
    public Button backButton;

    public Breadcrumb(String text, CategoryName categoryName) {
        setRoot();
        pathText.setText(text);

        if (categoryName != null) {
            backButton.setText("Gå tillbaka till " + categoryName.getPrettyName().toLowerCase() + ".");
        } else {
            destroyButton();
        }
    }

    public Breadcrumb(String text) {
        setRoot();
        pathText.setText(text);
        destroyButton();
    }

    public void destroyButton() {
//        getChildren().remove(backButton);
        System.out.println("remove tajm");
        backButton.setVisible(false);
    }

    public Button getBackButton() {
        return backButton;
    }

    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("breadcrumb.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
