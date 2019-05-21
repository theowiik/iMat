package Controller;

import Model.CartItem;
import Model.Checkout;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import se.chalmers.cse.dat216.project.ShoppingItem;

import javax.swing.text.html.ImageView;


public class CheckoutController {
    private static BackendController backendController;
    private Checkout checkout;


    //private Scrollbar shoppingCart;


    public CheckoutController() {
        backendController = BackendController.getInstance();
        checkout = new Checkout();
        updateView();
        setDefaultDate();
    }


    public void updateCartItemArea() {
        checkout.cartPane.getChildren().clear();
        for(ShoppingItem shoppingItem: backendController.getShoppingCart().getItems()) {
            checkout.cartPane.getChildren().add(new CartItem(shoppingItem.getProduct(), shoppingItem.getAmount()));
        }
    }

    public void updateView() {
        checkout.setWelcomeMessage("Var det bra så, " + getFirstName() + "?");
        checkout.setAmountMessage("Din kundvagn innehåller " + backendController.getShoppingCartAmount() + " varor.");
        checkout.setTotalText("TOTALT: " + getTotal() + "kr");
        updateCartItemArea();
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
