package Model;

import Controller.ProductCardController;
import Controller.iMatController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * A "card" includes information about a product
 */
public class ProductCard extends AnchorPane implements CustomComponent, ProductCardObservable {
    @FXML
    public Text name;
    @FXML
    public Text price;
    @FXML
    public Text unit;
    @FXML
    public ImageView image;
    @FXML
    public Label amountLabel;
    @FXML
    public Button addButton;
    @FXML
    public Button subtractButton;

    List<ProductCardObserver> observers = new ArrayList<>();
    private int amount = 0;
    private Product product;

    /**
     * Creates a new product card.
     *
     * @param product          a product.
     */
    ProductCard(Product product, Image image) {
        setRoot();
        this.product = product;
        this.name.setText(product.getName());
        this.price.setText(String.valueOf(product.getPrice()));
        this.unit.setText(product.getUnit());
        this.image.setImage(image);
        updateAmountLabel();
        addListenersToButtons();
    }

    /**
     * Updates the amount.
     * @param amount
     */
    private void setAmountLabel(int amount) {
        amountLabel.setText(String.valueOf(amount));
    }

    // FIXME: 2019-05-14
    // Uuuuugly. Had problems with adding a controller to it.
    // Is not planned to be fixed as I dont want to spend more time on this
    // because its only a prototype.
    private void addListenersToButtons() {
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                add();
            }
        });

        subtractButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                subtract();
            }
        });
    }

    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void add() {
        amount++;
        updateAmountLabel();
        notifyAllObserversProductAdded(this.product);
    }

    private void updateAmountLabel() {
        setAmountLabel(amount);
    }

    public void subtract() {
        if (amount > 0) {
            amount--;
            updateAmountLabel();
            notifyAllObserversProductRemoved(this.product);
        }
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public void notifyAllObserversProductAdded(Product product) {
        for (ProductCardObserver productCardObserver : observers) {
            productCardObserver.productAdded(product);
            System.out.println("Clicked! (+)");
        }
    }

    @Override
    public void notifyAllObserversProductRemoved(Product product) {
        for (ProductCardObserver productCardObserver : observers) {
            productCardObserver.productRemoved(product);
            System.out.println("Clicked! (-)");
        }
    }

    @Override
    public void addObserver(ProductCardObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeAllObservers() {
    }
}
