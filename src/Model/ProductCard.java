package Model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A "card" includes information about a product
 */
public class ProductCard extends AnchorPane implements CustomComponent, AddProductObservable {
    @FXML
    public Text name;
    @FXML
    public Text priceAndUnit;
    @FXML
    public ImageView image;
    @FXML
    public Button addProdButton;
    @FXML
    public Button subProdButton;
    @FXML
    public TextField productAmount;

    List<AddProductObserver> observers = new ArrayList<>();
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
        this.priceAndUnit.setText((String.valueOf(product.getPrice())) + " " + product.getUnit());
        this.image.setImage(image);
        updateAmountLabel();
        addListenersToButtons();
    }

    /**
     * Updates the amount.
     * @param amount
     */
    private void setAmountLabel(int amount) {
        productAmount.setText(String.valueOf(amount));
    }

    // FIXME: 2019-05-14
    // Uuuuugly. Had problems with adding a controller to it.
    // Is not planned to be fixed as I dont want to spend more time on this
    // because its only a prototype.
    private void addListenersToButtons() {
        addProdButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                add();
            }
        });

        subProdButton.setOnAction(new EventHandler<ActionEvent>() {
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
        for (AddProductObserver addProductObserver : observers) {
            addProductObserver.productAdded(product);
            System.out.println("Clicked! (+)");
        }
    }

    @Override
    public void notifyAllObserversProductRemoved(Product product) {
        for (AddProductObserver addProductObserver : observers) {
            addProductObserver.productRemoved(product);
            System.out.println("Clicked! (-)");
        }
    }

    @Override
    public void addObserver(AddProductObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeAllObservers() {
    }
}
