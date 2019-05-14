package Controller;

import Model.MyAccount;
import se.chalmers.cse.dat216.project.Order;

import java.util.List;

public class MyAccountController {
    private static BackendController backendController;
    private MyAccount myAccount;

    public MyAccountController() {
        backendController = BackendController.getInstance();
        myAccount = new MyAccount(this.getReciepts());
    }

    public MyAccount getMyAccount() {
        return myAccount;
    }

    public List<Order> getReciepts() {
        return BackendController.getInstance().getReciepts();
    }
}
