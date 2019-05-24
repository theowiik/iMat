package Controller;

import Model.CartItem;
import Model.ProductCard;
import Model.AddProductObserver;
import Model.ShoppingCart1;
import se.chalmers.cse.dat216.project.*;

import java.util.List;

public class ShoppingCartController implements ShoppingCartListener{

    private BackendController backendController;
    public ShoppingCart1 shoppingCart1;
    private List<ShoppingItem> cart;
    private ShoppingCart shoppingCart;
    CartItem cartItem;

    public ShoppingCartController() {
        this.backendController = BackendController.getInstance();
        this.shoppingCart1 = new ShoppingCart1();
        BackendController.getInstance().getShoppingCart().addShoppingCartListener(this);
    }

    public ShoppingCart1 getShoppingCart1() {
        return shoppingCart1;
    }

    public void updateCartItemArea() {
        int x = 0;
        shoppingCart1.cartItemArea.getChildren().clear();
        for(ShoppingItem shoppingItem: backendController.getShoppingCart().getItems()) {

                CartItem cartItem = new CartItem(shoppingItem.getProduct(), shoppingItem.getAmount());
                cartItem.addObserver(backendController);

                shoppingCart1.cartItemArea.getChildren().add(cartItem);

                shoppingCart1.scPane.vvalueProperty().setValue(shoppingCart1.scPane.getVmax());
            if ((x & 1) == 0) {

            } else {
                cartItem.pane.setStyle("-fx-background-color: #DDDDDD");
            }
            x++;
        }
    }

    public void updateTotCost(){
        String s = String.format("%.2f", backendController.getShoppingCart().getTotal());
        shoppingCart1.totalCostLabel.setText(s + " kr");
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        updateCartItemArea();
        updateTotCost();
    }
}
