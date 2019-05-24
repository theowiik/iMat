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
import se.chalmers.cse.dat216.project.Product;
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

    //public recieptItemTitled recieptItemTitled;
    public List<recieptItemTitled> reciepts = new ArrayList<>();

    private MyAccountShoppingList msl;
    private MyAccount account;

/*
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

 */


    public void addListenerToAllReciepts(MyAccountShoppingList masl, MyAccount account){
        msl = masl; //"spara lyssnare"
        this.account = account;
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
        //reciepts.get(0).saveAsShoppingList();
    }

    public MyAccountReciept(List<Order> reciepts) {
        setRoot();
        //spawnReciepts(reciepts);
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
    public void createReciept(List<Order> orders) {

        for (Order order : orders){
            recieptItemTitled rit = new recieptItemTitled(order.getDate().toString(), String.valueOf(order.getOrderNumber()), getPrice(order.getItems()));
            rit.addProductsList(order.getItems());
            rit.spawncartItems();
            rit.addListener(this.msl);
            rit.addListener(this.account);
            reciepts.add(rit);
        }

        updateReciepts();
    }

    private double getPrice(List<ShoppingItem> items) {
        double price = 0;
        for (ShoppingItem si : items)
            price += (si.getProduct().getPrice() * si.getAmount());
        return price;
    }

    private void updateReciepts() {
        clearAccordion();
        TitledPane tp;
        if (reciepts.size()>1){
            for (int i = reciepts.size()-1 ; i > 0; i--){
                tp = reciepts.get(i);
                tp.setAnimated(true);
                recieptAccordion.getPanes().add(tp);
            }
        } else {
            tp = reciepts.get(0);
            tp.setAnimated(true);
            recieptAccordion.getPanes().add(tp);
        }

    }

    private void clearAccordion() {
        recieptAccordion.getPanes().clear();
    }
}
