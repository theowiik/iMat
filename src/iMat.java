import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**
 * iMat
 */
public class iMat extends Application {

    /**
     * Starts the application
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception  {
        Parent root = FXMLLoader.load(getClass().getResource("view/iMat.fxml"));
        Scene scene = new Scene(root, 1280, 720);
        stage.setTitle("iMat");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
