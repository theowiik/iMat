import Controller.WindowResizeObservable;
import Controller.WindowResizeObserver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * iMat
 */
public class iMat extends Application implements WindowResizeObservable {
    private List<WindowResizeObserver> observers = new ArrayList<>();

    /**
     * Starts the application
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception  {
        ResourceBundle bundle = java.util.ResourceBundle.getBundle("resources/iMat");
        Parent root = FXMLLoader.load(getClass().getResource("Model/iMat.fxml"), bundle);
        Scene scene = new Scene(root, 1280, 720);
        stage.setMinWidth(750);
        stage.setMinHeight(700);
        stage.setTitle("iMat");
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void addObserver(WindowResizeObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (WindowResizeObserver observer : observers) {
            observer.windowIsResized();
        }
    }
}
