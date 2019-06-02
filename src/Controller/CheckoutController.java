package Controller;

import Model.CartItem;
import Model.Checkout;
import Model.MyAccountReciept;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

public class CheckoutController implements ShoppingCartListener {
    private static BackendController backendController;
    private Checkout checkout;


    //private Scrollbar shoppingCart;


    public CheckoutController(MyAccountReciept mar) {
        backendController = BackendController.getInstance();
        checkout = new Checkout(mar);
        updateView();
        setDefaultDate();
        BackendController.getInstance().getShoppingCart().addShoppingCartListener(this);
    }


    public void updateCartItemArea() {
        int x = 0;
        checkout.cartPane.getChildren().clear();
        for(ShoppingItem shoppingItem: backendController.getShoppingCart().getItems()) {
            CartItem cartItem = new CartItem(shoppingItem.getProduct(), shoppingItem.getAmount());
            checkout.cartPane.getChildren().add(cartItem);
            cartItem.addObserver(backendController);
            if ((x & 1) == 0) {

            } else {
                cartItem.pane.setStyle("-fx-background-color: #DDDDDD");
            }
            x++;
        }
    }

    public void responsiveEnabling(){
        checkout.responsiveEnabling();
    }

    public void updateView() {
        checkout.setWelcomeMessage("Var det bra så, " + getFirstName() + "?");
        checkout.setAmountMessage("Din kundvagn innehåller " + backendController.getShoppingCartAmount() + " varor.");
        setTotText();
        checkout.populateCurrent();
        checkout.populateCard();
        updateCartItemArea();
        //updateFinalWindow();
    }

    public void setTotText() {
        double i = getTotal();
        i = Math.ceil(i);
        String s = String.format("%.0f", i);
        checkout.setTotalText("TOTALT: " + s + "kr");
    }
/*    public void updateFinalWindow() {
        checkout.updateFinalWindow();
    }*/

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
        checkout.d1.setStyle("fx-background-color: #4CAF50;");
        checkout.handled1();
        checkout.confirmDeliveryDate();
        checkout.setDeliveryDateText();
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        updateCartItemArea();
        setTotText();
    }
}
