package Controller;

import Model.CartItem;
import Model.ProductCard;
import Model.AddProductObserver;
import Model.ShoppingCart1;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;
import se.chalmers.cse.dat216.project.ShoppingCart;

import java.util.List;

public class ShoppingCartController implements AddProductObserver {

    private BackendController backendController;
    private ShoppingCart1 shoppingCart1;
    private List<ShoppingItem> cart;
    private ShoppingCart shoppingCart;
    CartItem cartItem;
//    CheckoutController checkoutController;
    //ProductCard productCard;

    public ShoppingCartController() {
        this.backendController = BackendController.getInstance();
        this.shoppingCart1 = new ShoppingCart1();
        for (ProductCard productCard : backendController.getProductCardMap().values()) {
            productCard.addObserver(this);

        }
    }

    public ShoppingCart1 getShoppingCart1() {
        return shoppingCart1;
    }

    @Override
    public void productAdded(Product product) {
        updateCartItemArea();
        updateTotCost();
    }

    @Override
    public void productRemoved(Product product) {
        updateCartItemArea();
        updateTotCost();
    }

    public void updateCartItemArea() {
        shoppingCart1.cartItemArea.getChildren().clear();
        for(ShoppingItem shoppingItem: backendController.getShoppingCart().getItems()) {
            CartItem cartItem = new CartItem(shoppingItem.getProduct(), shoppingItem.getAmount());

            cartItem.addObserver(backendController);
            cartItem.addObserver(this);

            shoppingCart1.cartItemArea.getChildren().add(cartItem);
        }
    }

    public void updateTotCost(){
        shoppingCart1.totalCostLabel.setText(String.valueOf(backendController.getShoppingCart().getTotal())+ " kr");
    }
}
