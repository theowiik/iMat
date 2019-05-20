package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import javax.swing.text.html.ImageView;
import java.io.IOException;

public class Checkout extends AnchorPane implements CustomComponent {

    public String deliveryDate;
    public String selectedDeliveryDate;

    @FXML
    public Text welcomeMessage;
    @FXML
    public Text amountMessage;
    @FXML
    public Text totalText;
    @FXML
    public Text totalText1;
    @FXML
    public Text deliveryDateText;

    @FXML
    public Button changeDeliveryButton;
    @FXML
    public Button confirmDeliveryButton;
    @FXML
    public Button backDeliveryButton;
    @FXML
    public Button nextDateButton1;
    @FXML
    public Button nextDateButton2;

    @FXML
    public Button d1;
    @FXML
    public Button d2;
    @FXML
    public Button d3;
    @FXML
    public Button d4;
    @FXML
    public Button d5;
    @FXML
    public Button d6;
    @FXML
    public Button d7;
    @FXML
    public Button d8;
    @FXML
    public Button d9;
    @FXML
    public Button d10;
    @FXML
    public Button d11;

    @FXML
    public AnchorPane deliveryWindow1;

    @FXML
    public AnchorPane deliveryWindow2;

    @FXML
    public AnchorPane mainWindow;

    @FXML
    public AnchorPane shadowWindow;

    @FXML
    public AnchorPane payViewWindow;

    @FXML
    public AnchorPane infoCoverWindow;

    @FXML
    public AnchorPane invoiceInfoWindow;

    @FXML
    public AnchorPane bankInfoWindow;



    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage.setText(welcomeMessage);
    }

    public void setAmountMessage(String amountMessage) {
        this.amountMessage.setText(amountMessage);
    }

    public void setTotalText(String totalText) {
        this.totalText.setText(totalText);
        this.totalText1.setText((totalText));
    }

    public void setDeliveryDateText(){
        System.out.println("Här");
        this.deliveryDateText.setText(deliveryDate);
        System.out.println(deliveryDate);
    }

    public void setSelectedDeliveryDate(String s) {
        selectedDeliveryDate = s;
    }

    public void confirmDeliveryDate() {
        this.deliveryDate = selectedDeliveryDate;
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

    @FXML
    public void openDeliveryWindow() {
        this.shadowWindow.toFront();
    }

    @FXML
    public void closeDeliveryWindow() {
        this.mainWindow.toFront();
    }

    @FXML
    public void openMainWindow() {
        this.mainWindow.toFront();
    }

    @FXML
    public void acceptDelivery() {
        this.mainWindow.toFront();
        confirmDeliveryDate();
        setDeliveryDateText();
    }

    @FXML
    public void nextDateView1() {
        this.deliveryWindow2.toFront();
    }

    @FXML
    public void nextDateView2() {
        this.deliveryWindow1.toFront();
    }

    @FXML
    public void openPayViewWindow() {
        this.payViewWindow.toFront();
    }

    @FXML
    public void uncoverInvoiceInfoWindow() {
        this.invoiceInfoWindow.toFront();
    }

    @FXML
    public void uncoverBankInfoWindow() {
        this.bankInfoWindow.toFront();
    }

    @FXML
    public void hideInfo() {
        this.infoCoverWindow.toFront();
    }

    @FXML
    public void handled1() {
        setSelectedDeliveryDate(d1.getText());
    }
    @FXML
    public void handled2() {
        setSelectedDeliveryDate(d2.getText());
    }
    @FXML
    public void handled3() {
        setSelectedDeliveryDate(d3.getText());
    }
    @FXML
    public void handled4() {
        setSelectedDeliveryDate(d4.getText());
    }
    @FXML
    public void handled5() {
        setSelectedDeliveryDate(d5.getText());
    }
    @FXML
    public void handled6() {
        setSelectedDeliveryDate(d6.getText());
    }
    @FXML
    public void handled7() {
        setSelectedDeliveryDate(d7.getText());
    }
    @FXML
    public void handled8() {
        setSelectedDeliveryDate(d8.getText());
    }
    @FXML
    public void handled9() {
        setSelectedDeliveryDate(d9.getText());
    }
    @FXML
    public void handled10() {
        setSelectedDeliveryDate(d10.getText());
    }
    @FXML
    public void handled11() {
        setSelectedDeliveryDate(d11.getText());
    }
}
