package Controller;

import Model.MyAccount;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.util.List;

public class MyAccountController {
    private static BackendController backendController;
    private MyAccount myAccount;

    public MyAccountController() {
        backendController = BackendController.getInstance();
        myAccount = new MyAccount();
    }

    public MyAccount getMyAccount() {
        return myAccount;
    }

    public List<Order> getReciepts() {
        return BackendController.getInstance().getReciepts();
    }

    public void contactInfoToFront() {
        myAccount.contactInfoToFront();
    }

    public void recieptsToFront() {
        myAccount.recieptsToFront();
    }

    public void listsToFront() {
        myAccount.listsToFront();
    }

    public void personalDiscountToFront() { myAccount.personalDiscountToFront();}

    public void clearFieldsContact() {myAccount.clearFieldsContact();}

    public void addToCart() {myAccount.addToCart();}

    public void addProductToShoppingList(ShoppingItem p ) {
        myAccount.myAccountShoppingList.listItemTitled.addProduct(p);
    }

    public void saveAsShoppingList() {
        myAccount.saveAsShoppingList();
    }

    public void updateContactInfoFields() {
        myAccount.updateContactInfoFields();
    }
}
