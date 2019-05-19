package Controller;

import Model.Checkout;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.swing.text.html.ImageView;


public class CheckoutController {
    private static BackendController backendController;
    private Checkout checkout;


    //private Scrollbar shoppingCart;


    public CheckoutController() {
        backendController = BackendController.getInstance();
        checkout = new Checkout();
        updateView();
    }

    public void updateView() {
        checkout.setWelcomeMessage("Var det bra så, " + getFirstName() + "?");
        checkout.setAmountMessage("Din kundvagn innehåller " + backendController.getShoppingCartAmount() + " varor.");
        checkout.setTotalText("TOTALT: " + getTotal() + "kr");
        setDefaultDate();
    }

    public Checkout getCheckout() {
        return checkout;
    }

    private String getFirstName() {
        return backendController.getFirstName();
    }

    private double getTotal() {
        return backendController.getTotal();
    }

    private void setDefaultDate() {
        checkout.handled1();
        checkout.confirmDeliveryDate();
        checkout.setDeliveryDateText();
    }


}
