package Controller;

import Model.CartItem;
import Model.ProductCard;
import Model.ProductCardObserver;
import Model.ShoppingCart1;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;
import se.chalmers.cse.dat216.project.ShoppingCart;

import java.util.List;

public class ShoppingCartController implements ProductCardObserver {

    private BackendController backendController;
    private ShoppingCart1 shoppingCart1;
    private List<ShoppingItem> cart;
    private ShoppingCart shoppingCart;
    CartItem cartItem;
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
        System.out.println("Nu är du här");
        shoppingCart1.cartItemArea.getChildren().add(new CartItem(product));
        //updateCartItemArea();
    }

    @Override
    public void productRemoved(Product product) {

    }

    public void updateCartItemArea() {
        cart = shoppingCart.getItems();
        for(ShoppingItem shop : cart) {
            CartItem cartItem = new CartItem(shop.getProduct());
            shoppingCart1.cartItemArea.getChildren().add(cartItem);
        }
    }
}
