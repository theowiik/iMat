package Controller;

import Model.Checkout;

public class CheckoutController {
    private static BackendController backendController;
    private Checkout checkout;

    public CheckoutController() {
        backendController = BackendController.getInstance();
        checkout = new Checkout();
    }

    public Checkout getCheckout() {
        return checkout;
    }
}
