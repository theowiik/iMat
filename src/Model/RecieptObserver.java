package Model;

import se.chalmers.cse.dat216.project.ShoppingItem;

import java.util.ArrayList;

public interface RecieptObserver {

    void ShoppingListAdded(ArrayList<ShoppingItem> shoppingitem);
}
