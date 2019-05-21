package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Order;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class MyAccountReciept extends AnchorPane implements CustomComponent {

    @FXML
    public AnchorPane recieptArea;

    @FXML
    public ScrollPane scrollReciepts;

    @FXML
    public Accordion recieptAccordion;


    public void spawnReciepts(List<Order> reciepts) {
        /*
        recieptItemTitled ri;
        for (Order order : reciepts){
            ri = new recieptItemTitled(order.getDate().toString(), "Test", 22.00);
            recieptArea.getChildren().add(ri);
        }
        */

        TitledPane reciept = new recieptItemTitled("2019-05-10", "Emilia", 2.3);
        reciept.setAnimated(true);
        recieptAccordion.getPanes().add(reciept);
        reciept = new recieptItemTitled("2019-05-12", "Emil", 433.23);
        reciept.setAnimated(true);
        recieptAccordion.getPanes().add(reciept);

    }

    public MyAccountReciept(List<Order> reciepts) {
        setRoot();
        spawnReciepts(reciepts);
        scrollReciepts.setFitToWidth(true);
    }

    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myAccountReciept.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
