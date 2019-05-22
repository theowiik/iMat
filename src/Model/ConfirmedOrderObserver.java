package Model;

import se.chalmers.cse.dat216.project.Order;

import java.util.List;

public interface ConfirmedOrderObserver {
    void createReciept(List<Order> orders);
}
