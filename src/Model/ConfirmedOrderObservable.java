package Model;

public interface ConfirmedOrderObservable {

    void addObserver(ConfirmedOrderObserver coo);
    void addRecieptFromOrder();
}
