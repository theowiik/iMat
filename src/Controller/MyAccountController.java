package Controller;

import Model.MyAccount;

public class MyAccountController {
    private static BackendController backendController;
    private MyAccount myAccount;

    public MyAccountController() {
        backendController = BackendController.getInstance();
        myAccount = new MyAccount();
    }

    public MyAccount getMyAccount() {
        return myAccount;
    }
}
