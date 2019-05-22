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
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyAccountReciept extends AnchorPane implements CustomComponent, ConfirmedOrderObserver {

    @FXML
    public AnchorPane recieptArea;

    @FXML
    public ScrollPane scrollReciepts;

    @FXML
    public Accordion recieptAccordion;

    public recieptItemTitled recieptItemTitled;
    public ArrayList<recieptItemTitled> reciepts = new ArrayList<>();


    public void spawnReciepts(List<Order> reciepts) {

        recieptItemTitled ri;
        TitledPane tp;
        for (Order order : reciepts){
            List<ShoppingItem> items = order.getItems();
            ri = new recieptItemTitled(order.getDate().toString(), "Test", 22.00);
            double price = 0;
            for (ShoppingItem si : items) {
                ri.addProduct(si);
                price += (si.getProduct().getPrice() * si.getAmount());
            }
            ri.spawncartItems();
            tp = ri;
            tp.setAnimated(true);
            recieptAccordion.getPanes().add(tp);
            ri.price.setText(String.valueOf(price));
            ri.lev.setText("Emilia");
        }


        spawnExamples();
    }

    public void addListenerToAllReciepts(MyAccountShoppingList masl){
        for (recieptItemTitled rit : reciepts)
            rit.addListener(masl);
    }

    private void spawnExamples(){
        TitledPane reciept = new recieptItemTitled("2019-05-10", "Emilia", 2.3);
        reciept.setAnimated(true);
        recieptAccordion.getPanes().add(reciept);
        reciept = new recieptItemTitled("2019-05-12", "Emil", 433.23);
        reciept.setAnimated(true);
        recieptAccordion.getPanes().add(reciept);
    }

    public void saveAsShoppingList(){
        //recieptItemTitled.saveAsShoppingList();
    }

    public MyAccountReciept(List<Order> reciepts) {
        setRoot();
        spawnReciepts(reciepts);
        scrollReciepts.setFitToWidth(true);
    }

    public void newReciept(RecieptItem ri) {
        double price = 0;
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



    @Override
    public void createReciept(List<Order> order) {

    }
}
