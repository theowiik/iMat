package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;


public class WizardView extends AnchorPane implements CustomComponent{

    @FXML
    public Label step1;

    @FXML
    public Label step2;

    @FXML
    public Label step3;

    @FXML
    public Label step4;

    @FXML
    public ImageView indicator;

    private ArrayList<Label> labels = new ArrayList<>();
    private Label tmp = step1;
    private int step;

    public WizardView(int step) {
        setRoot();
        addLabels();
        setFocus(step);
        this.step = step;

    }

    private void addLabels() {
        labels.add(step1);
        labels.add(step2);
        labels.add(step3);
        labels.add(step4);
    }

    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("wizardView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setFocus(int step) {
        if (step > 1)
            tmp.setStyle("-fx-font-size: 20 px;");
        if (step < 5){
            String path = "resources/step" + step + ".png";
            indicator.setImage(new Image(path));
            tmp = labels.get(step-1);
            tmp.setStyle("-fx-text-fill: #7B2C1B");
            tmp.setStyle("-fx-font-size: 30 px;");
        }


    }

    public void spawnNextStep() {
        setFocus(++step);
    }


    public void spawnPriorStep() {
        setFocus(--step);
    }
}
