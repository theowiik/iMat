package Model;

import se.chalmers.cse.dat216.project.ShoppingItem;

import java.util.ArrayList;
import java.util.List;

public interface RecieptObserver {

    void ShoppingListAdded(List<ShoppingItem> shoppingitem);
}
