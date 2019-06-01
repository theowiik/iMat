package Controller;

import Model.*;
import javafx.event.EventHandler;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCartListener;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The main controller for the application window.
 */
public class iMatController implements Initializable, WindowResizeObserver, AddProductObserver, ShoppingCartListener {
    private static BackendController backendController;

    private ProductBrowserController productBrowserController;
    private MyAccountController myAccountController;
    private CheckoutController checkoutController;
    private ShoppingCartController shoppingCartController;
    private Help help;


    private int cardSize = 300;

    @FXML
    public AnchorPane contentPane;
    @FXML
    public TextField searchBar;

    @FXML
    public AnchorPane storeButton;
    @FXML
    public AnchorPane checkoutButton;
    @FXML
    public AnchorPane myAccountButton;
    @FXML
    public AnchorPane cartButton;

    @FXML
    public Label amountOfItemsInCart;

    private String activeColor = "#be5250";
    private String inActiveColor = "";

    private String activeTabColor = "white";
    private String inActiveTabColor = "#4a4a4a";

    @FXML
    public Text storeTitle;
    @FXML
    public Text myAccountTitle;
    @FXML
    public Text cartTitle;
    @FXML
    public Text checkoutTitle;


    @FXML
    public Text cartText;

    @FXML
    public Label totalPrice;

    /**
     * Initializes iMatController
     *
     * @param location  location.
     * @param resources a resources file.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backendController = BackendController.getInstance();
        backendController.initCategories();

        productBrowserController = new ProductBrowserController();
        myAccountController = new MyAccountController();
        checkoutController = new CheckoutController(myAccountController.getMyAccount().getRecieptView());

        shoppingCartController = new ShoppingCartController();

        observeAllProductCards();
        backendController.db.getShoppingCart().addShoppingCartListener(this);

        spawnProductBrowser();
        spawnMyAccount();
        spawnCheckout();
        spawnShoppingCart();
        spawnHelp();

        eventHandlerMethod();

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
        help = new Help(this);
        contentPane.getChildren().add(help);
    }

    /**
     * Shows the shopping cart view
     */
    private void spawnShoppingCart() {
        ShoppingCart1 shoppingCart1 = shoppingCartController.getShoppingCart1();
        contentPane.getChildren().add(shoppingCart1);
    }

    /**
     * Brings the store view to the front.
     */
    public void storeToFront() {
        System.out.println("Bringing store view to front...");
        shoppingCartController.getShoppingCart1().isInFront = false;
        productBrowserController.getProductBrowser().toFront();
        storeActive(true);
        checkoutActive(false);
        myAccountActive(false);
        cartActive(shoppingCartController.getShoppingCart1().isInFront);
    }

    /**
     * Brings the account view to the front.
     */
    public void myAccountToFront() {
        System.out.println("Bringing my account view to front...");
        shoppingCartController.getShoppingCart1().isInFront = false;
        myAccountController.getMyAccount().toFront();
        storeActive(false);
        checkoutActive(false);
        myAccountActive(true);
        cartActive(shoppingCartController.getShoppingCart1().isInFront);
    }

    /**
     * Bring the checkout view to the front.
     */
    public void checkoutToFront() {
        System.out.println("Bringing checkout to front...");
        shoppingCartController.getShoppingCart1().isInFront = false;
        checkoutController.getCheckout().toFront();
        checkoutController.updateView();
/*        if (checkoutController.getCheckout().orderIsFinished) {
            checkoutController.getCheckout().orderIsFinished = false;
            checkoutController.getCheckout().openMainWindow();
        }*/
        checkoutController.getCheckout().openMainWindow();
        storeActive(false);
        checkoutActive(true);
        myAccountActive(false);
        cartActive(shoppingCartController.getShoppingCart1().isInFront);
    }

    /**
     * Bring the help view to front.
     */
    public void helpToFront() {
        System.out.println("Bringing help to front...");
        shoppingCartController.getShoppingCart1().isInFront = false;
        help.toFront();
        help.populateFields();
    }

    public void helpToBack() {
        shoppingCartController.getShoppingCart1().isInFront = false;
        help.toBack();
    }

    /**
     * Bring the shopping cart view to the front.
     */
    public void shoppingCartToFront() {
        if (!shoppingCartController.getShoppingCart1().isInFront) {
            System.out.println("Bringing my shopping cart view to front...");
            shoppingCartController.getShoppingCart1().toFront();
            shoppingCartController.getShoppingCart1().isInFront = true;
            storeActive(false);
            checkoutActive(false);
            myAccountActive(false);
            cartActive(shoppingCartController.getShoppingCart1().isInFront);
        } else {
            shoppingCartController.getShoppingCart1().toBack();
            shoppingCartController.getShoppingCart1().isInFront = false;
            storeActive(false);
            checkoutActive(false);
            myAccountActive(false);
            cartActive(shoppingCartController.getShoppingCart1().isInFront);
        }
    }

    public void setAmountOfItemsInCart() {
        amountOfItemsInCart.setText(String.valueOf(backendController.getTotalAmountOfItems()));
        amountOfItemsInCart.setVisible(true);

        double i = backendController.getShoppingCart().getTotal();
        i = Math.ceil(i);
        String s = String.format("%.0f", i);
        totalPrice.setText(s + " kr");
        totalPrice.setVisible(true);
    }

    public void logoPressed() {
        storeActive(true);
        storeToFront();
        myAccountActive(false);
        cartActive(false);
        checkoutActive(false);
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
        storeToFront();
        productBrowserController.spawnTitledSection("Sökresultat för: " + query);
        productBrowserController.spawnProductCardGrid(products);
    }

    private void storeActive(boolean state) {
        String color = (state) ? activeColor : inActiveColor;
        storeButton.setStyle("-fx-background-color: " + color);
        productBrowserController.showAllProducts();
        setTextOnTabClick(storeTitle, state);
    }

    private void setTextOnTabClick(Text title, boolean state) {
        String color = (state) ? activeTabColor : inActiveTabColor;
        title.setStyle("-fx-fill: " + color + "; -fx-font-weight: bold");
    }

    public void cartActive(boolean state) {
        String color = (state) ? activeColor : inActiveColor;
        cartButton.setStyle("-fx-background-color: " + color);
        setTextOnTabClick(cartTitle, state);
    }

    private void myAccountActive(boolean state) {
        String color = (state) ? activeColor : inActiveColor;
        myAccountButton.setStyle("-fx-background-color: " + color);
        setTextOnTabClick(myAccountTitle, state);
    }

    private void checkoutActive(boolean state) {
        String color = (state) ? activeColor : inActiveColor;
        checkoutButton.setStyle("-fx-background-color: " + color);
        setTextOnTabClick(checkoutTitle, state);
    }

    @Override
    public void productAdded(Product product) {
        // Animate added product
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(3000));
                setInterpolator(Interpolator.EASE_OUT);
            }

            @Override
            protected void interpolate(double frac) {
//                Color vColor = new Color(0, 1, 0, 1 - frac);
                Color vColor = new Color(0.05, 0.90, 0.5, 1 - frac);
                cartButton.setBackground(new Background(new BackgroundFill(vColor, CornerRadii.EMPTY, Insets.EMPTY)));

                FadeTransition ft = new FadeTransition(Duration.millis(7000), cartText);
                cartText.setText(product.getName() + " tillagd!");
                ft.setFromValue(1.0);
                ft.setToValue(0);
                ft.setCycleCount(1);
                ft.setAutoReverse(true);

                ft.play();

            }
        };
        animation.play();

        // Change text
        //cartText.setText(product.getName() + " tillagt i din varukorg!");
        setAmountOfItemsInCart();
    }

    @Override
    public void productRemoved(Product product) {

        final Animation animation = new Transition() {

            {
                setCycleDuration(Duration.millis(3000));
                setInterpolator(Interpolator.EASE_OUT);
            }

            @Override
            protected void interpolate(double frac) {
                //Color vColor = new Color(1, 0, 0, 1 - frac);
                Color vColor = new Color(0.89, 0.45, 0.45, 1 - frac);
                cartButton.setBackground(new Background(new BackgroundFill(vColor, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        };
        animation.play();
        setAmountOfItemsInCart();
    }

    private void observeAllProductCards() {
        for (ProductCard productCard : backendController.getProductCardMap().values()) {
            productCard.addObserver(this);
        }
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        setAmountOfItemsInCart();
    }

    private void eventHandlerMethod() {
        checkoutController.getCheckout().buyMoreButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        storeToFront();
                    }
                });
        checkoutController.getCheckout().buyMoreButton1.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        storeToFront();
                    }
                });
        checkoutController.getCheckout().buyMoreButton2.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        storeToFront();
                    }
                });
        checkoutController.getCheckout().buyMoreButton3.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        storeToFront();
                    }
                });
        checkoutController.getCheckout().buyMoreButton4.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        storeToFront();
                    }
                });

        shoppingCartController.shoppingCart1.toCheckoutButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        checkoutToFront();
                    }
                });
        shoppingCartController.shoppingCart1.closeButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        shoppingCartToFront();
                    }
                });
        shoppingCartController.shoppingCart1.closeShoppingCart.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        shoppingCartToFront();
                    }
                });
    }
}
