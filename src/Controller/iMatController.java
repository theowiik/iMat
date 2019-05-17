package Controller;

import Model.Checkout;
import Model.Help;
import Model.MyAccount;
import Model.ProductBrowser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The main controller for the application window.
 */
public class iMatController implements Initializable, WindowResizeObserver {
    private static BackendController backendController;

    private ProductBrowserController productBrowserController;
    private MyAccountController myAccountController;
    private CheckoutController checkoutController;
    private Help help;

    private int cardSize = 300;

    @FXML
    public AnchorPane contentPane;
    @FXML
    public TextField searchBar;

    /**
     * Initializes iMatController
     * @param location location.
     * @param resources a resources file.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backendController = BackendController.getInstance();

        productBrowserController = new ProductBrowserController();
        myAccountController = new MyAccountController();
        checkoutController = new CheckoutController();

        spawnProductBrowser();
        spawnMyAccount();
        spawnCheckout();
        spawnHelp();

        storeToFront();
    }

    /**
     * Shows the product browser
     */
    public void spawnProductBrowser() {
        ProductBrowser browser = productBrowserController.getProductBrowser();
        contentPane.getChildren().add(browser);
    }

    /**
     * Shows the my account view
     */
    public void spawnMyAccount() {
        MyAccount myAccount = myAccountController.getMyAccount();
        contentPane.getChildren().add(myAccount);
    }

    /**
     * Shows the my checkout view
     */
    private void spawnCheckout() {
        Checkout checkout = checkoutController.getCheckout();
        contentPane.getChildren().add(checkout);
    }

    private void spawnHelp() {
        help = new Help();
        contentPane.getChildren().add(help);
    }
    /**
     * Brings the store view to the front.
     */
    public void storeToFront() {
        System.out.println("Bringing store view to front...");
        productBrowserController.getProductBrowser().toFront();
    }

    /**
     * Brings the account view to the front.
     */
    public void myAccountToFront() {
        System.out.println("Bringing my account view to front...");
        myAccountController.getMyAccount().toFront();
    }

    /**
     * Bring the checkout view to the front.
     */
    public void checkoutToFront() {
        System.out.println("Bringing checkout to front...");
        checkoutController.getCheckout().toFront();
    }

    /**
     * Bring the help view to front.
     */
    public void helpToFront() {
        System.out.println("Bringing help to front...");
        help.toFront();
        help.populateFields();
    }

    /**
     * Calls all observers that the window has been resized.
     */
    @Override
    public void windowIsResized() {
        productBrowserController.updatePrefColumns(cardSize);
    }

    /**
     * Search
     */
    @FXML
    public void search() {
        String query = searchBar.getText();
        List<Product> products = backendController.search(query);
        productBrowserController.clearCardVBox();
        productBrowserController.spawnTitledSection("Sökresultat för: " + query);
        productBrowserController.spawnProductCardGrid(products);
    }
}
