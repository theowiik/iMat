package Model;

import Controller.BackendController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Customer;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyAccount extends AnchorPane implements CustomComponent{

    @FXML
    public AnchorPane rightContainer;

    @FXML
    public AnchorPane leftContainer;

    @FXML
    public AnchorPane hider;

    MyAccountReciept myAccountReciept;
    MyAccountContactInfo myAccountContactInfo;
    public MyAccountShoppingList myAccountShoppingList;
    MyAccountPersonalDiscounts myAccountPersonalDiscounts;

    public void spawnRecieptView() {
        List<Order> reciepts = BackendController.getInstance().getReciepts();

        reciepts.add(addMango());

        myAccountReciept = new MyAccountReciept(reciepts);
        rightContainer.getChildren().add(myAccountReciept);
    }

    private Order addMango(){
        Order order = new Order();
        order.setDate(new Date());
        order.setOrderNumber(1);
        List<ShoppingItem> sl = new ArrayList<>();
        ShoppingItem si = new ShoppingItem(BackendController.getInstance().getProduct(45));
        sl.add(si);
        order.setItems(sl);

        return order;
    }

    public void spawnContactInfoView() {
        myAccountContactInfo = new MyAccountContactInfo();
        rightContainer.getChildren().add(myAccountContactInfo);
    }

    public void spawnListView() {
        myAccountShoppingList = new MyAccountShoppingList();
        rightContainer.getChildren().add(myAccountShoppingList);
    }

    public void spawnPersonalDiscounts() {
        myAccountPersonalDiscounts = new MyAccountPersonalDiscounts();
        rightContainer.getChildren().add(myAccountPersonalDiscounts);
    }

    public void hideContent() {
        hider.toFront();
    }

    public MyAccount() {
        setRoot();
        spawnRecieptView();
        spawnContactInfoView();
        spawnListView();
        spawnPersonalDiscounts();
        recieptsToFront();
        hideContent();
    }

    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myAccountView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void contactInfoToFront() {
        myAccountContactInfo.toFront();
    }
    public void recieptsToFront() {
        myAccountReciept.toFront();
    }

    public void listsToFront() {
        myAccountShoppingList.toFront();
    }

    public void personalDiscountToFront(){
        myAccountPersonalDiscounts.toFront();
    }

    public void clearFieldsContact() {
        myAccountContactInfo.clearFieldsContact();
    }

    public void addToCart() {
        myAccountShoppingList.addToCart();
    }

    public void saveAsShoppingList() {
        myAccountReciept.saveAsShoppingList();
    }

    //public void saveFieldsContact(Customer c) {
    //    myAccountContactInfo.saveFieldsContact(c);
    //}
}
