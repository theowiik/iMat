package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Order;

import java.io.IOException;
import java.util.List;

public class MyAccountShoppingList extends AnchorPane implements CustomComponent {

    @FXML
    public AnchorPane recieptArea;

    @FXML
    public ScrollPane scrollLists;

    @FXML
    public Accordion listAccordion;


    public void spawnLists() {
        /*RecieptItem ri;
        for (Order order : reciepts){
            ri = new RecieptItem(order.getDate().toString(), "Test", 22.00);
            recieptArea.getChildren().add(ri);
        }
        RecieptItem recieptItem = new RecieptItem("2019-05-10", "Emilia", 23.50);
        recieptArea.getChildren().add(recieptItem);
        RecieptItem reit = new RecieptItem("2019-05-10", "Emil", 23.50);
        recieptArea.getChildren().add(reit);*/


        TitledPane listItem = new listItemTitled("Veckohandlingen", "Blandat", 1234.56);
        listItem.setAnimated(true);
        listAccordion.getPanes().add(listItem);
        listItem = new listItemTitled("Fredagskäket", "Middag", 845.25);
        listItem.setAnimated(true);
        listAccordion.getPanes().add(listItem);

    }

    public MyAccountShoppingList() {
        setRoot();
        spawnLists();
        scrollLists.setFitToWidth(true);
    }

    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myAccountShoppingList.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
