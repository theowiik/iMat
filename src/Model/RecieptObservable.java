package Model;

public interface RecieptObservable {

   void addListener(RecieptObserver observer);
   void notifyNewShoppingList();


}
