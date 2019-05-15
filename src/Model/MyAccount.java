package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Order;

import java.io.IOException;
import java.util.List;

public class MyAccount extends AnchorPane implements CustomComponent{

    @FXML
    public AnchorPane rightContainer;

    @FXML
    public AnchorPane leftContainer;


    MyAccountReciept myAccountReciept;
    MyAccountContactInfo myAccountContactInfo;
    MyAccountShoppingList myAccountShoppingList;
    MyAccountPersonalDiscounts myAccountPersonalDiscounts;

    public void spawnRecieptView(List<Order> reciepts) {
        myAccountReciept = new MyAccountReciept(reciepts);
        rightContainer.getChildren().add(myAccountReciept);
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

    public MyAccount(List<Order> reciepts) {
        setRoot();
        spawnRecieptView(reciepts);
        spawnContactInfoView();
        spawnListView();
        spawnPersonalDiscounts();
        recieptsToFront();
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

    public void personalDiscountsToFront(){
        myAccountPersonalDiscounts.toFront();
    }
}
