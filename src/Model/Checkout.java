package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import javax.swing.text.html.ImageView;
import java.io.IOException;

public class Checkout extends AnchorPane implements CustomComponent {

    public String deliveryDate;

    @FXML
    public Text welcomeMessage;
    @FXML
    public Text amountMessage;
    @FXML
    public Text totalText;
    @FXML
    public Text deliveryDateText;

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage.setText(welcomeMessage);
    }

    public void setAmountMessage(String amountMessage) {
        this.amountMessage.setText(amountMessage);
    }

    public void setTotalText(String totalText) {
        this.totalText.setText(totalText);
    }

    public void setDeliveryDateText(){
        this.deliveryDateText.setText(deliveryDate);
    }

    public Checkout() {
        setRoot();
    }

    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("checkoutView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
