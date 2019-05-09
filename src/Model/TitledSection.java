package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class TitledSection extends AnchorPane {
    @FXML
    private Text title;

    /**
     *
     */
    public TitledSection() {
        setRoot();
        this.title.setText("Sample text");
    }

    /**
     *
     * @param title
     */
    public TitledSection(String title) {
        setRoot();
        this.title.setText(title);
    }

    /**
     *
     * @param title
     * @param color
     */
    public TitledSection(String title, String color) {
        setRoot();
        this.title.setText(title);
        StringBuilder sb = new StringBuilder();
        sb.append("-fx-background-color: ");

        if (isValidColor(color)) {
            sb.append(color);
            System.out.println("hiiiiiiii");
            System.out.println(sb.toString());
            this.title.setStyle(sb.toString());
        }
    }

    /**
     * To prevent injecting custom css code.
     */
    private boolean isValidColor(String color) {
        return true;
    }

    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("titledSection.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
