package Controller;

import javafx.scene.layout.AnchorPane;

public interface View {
    /**
     * Displays itself
     */
    void display(AnchorPane parent);

    /**
     * Destroys itself.
     *
     * :(
     */
    void destroy();
}
