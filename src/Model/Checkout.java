package Model;

import Controller.BackendController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Checkout extends AnchorPane implements CustomComponent, ConfirmedOrderObservable{

    public String deliveryDate;
    public String selectedDeliveryDate;
    public boolean orderIsFinished;

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
    public Text deliveryDateText2;
    @FXML
    public Text orderNumberMessage;
    @FXML
    public Text deliveryMessage;


    @FXML
    public TextField fNameField;
    @FXML
    public TextField fNameField2;
    @FXML
    public TextField lNameField;
    @FXML
    public TextField lNameField2;
    @FXML
    public TextField addressField;
    @FXML
    public TextField addressField2;
    @FXML
    public TextField codeField;
    @FXML
    public TextField codeField2;
    @FXML
    public TextField cityField;
    @FXML
    public TextField cityField2;
    @FXML
    public TextField bankField;
    @FXML
    public TextField cardField;


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
    public AnchorPane deliveryViewWindow;
    @FXML
    public AnchorPane infoCoverWindow;
    @FXML
    public AnchorPane invoiceInfoWindow;
    @FXML
    public AnchorPane bankInfoWindow;
    @FXML
    public AnchorPane InvoiceCoverWindow;
    @FXML
    public AnchorPane invoiceInfoGrid;
    @FXML
    public AnchorPane finalWindow;

    @FXML
    public AnchorPane indicatorArea;
    public WizardView wizardView = new WizardView(1);

    @FXML
    public FlowPane cartPane;

    private ArrayList<ConfirmedOrderObserver> observers = new ArrayList<>();

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
        this.deliveryDateText.setText(deliveryDate);
        this.deliveryDateText2.setText(deliveryDate);
    }

    public void setSelectedDeliveryDate(String s) {
        selectedDeliveryDate = s;
    }

    public void confirmDeliveryDate() {
        this.deliveryDate = selectedDeliveryDate;
    }

    public void setDeliveryMessage() {
        deliveryMessage.setText("På " + deliveryDate + " kommer Emilia och levererar dina varor.");
    }

    public void setOrderNumberMessage() {
        BackendController bc = BackendController.getInstance();
        orderNumberMessage.setText("Ordernummer: " + bc.getLastOrderNumber());
    }

    public void spawnIndicator() {
        indicatorArea.getChildren().add(wizardView);
    }

    public Checkout(MyAccountReciept mar) {
        setRoot();
        addObserver(mar);
        spawnIndicator();
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

    public void updateFinalWindow() {
        setDeliveryMessage();
        setOrderNumberMessage();
    }

    @FXML
    public void openDeliveryWindow() {
        this.shadowWindow.toFront();
    }

    @FXML
    public void closeDeliveryWindow() {
        this.deliveryViewWindow.toFront();
        this.indicatorArea.toFront();
    }

    @FXML
    public void openMainWindow() {
        this.mainWindow.toFront();
        this.indicatorArea.toFront();
        wizardView.setFocus(1);
    }

    @FXML
    public void openDeliveryViewWindow() {
        this.deliveryViewWindow.toFront();
        this.indicatorArea.toFront();
        wizardView.setFocus(2);
    }

    @FXML
    public void acceptDelivery() {
        this.deliveryViewWindow.toFront();
        this.indicatorArea.toFront();
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
        this.indicatorArea.toFront();
        wizardView.setFocus(3);
    }

    public void openFinalWindow() {
        finalWindow.toFront();
        this.indicatorArea.toFront();
        wizardView.setFocus(4);
    }

    @FXML
    public void uncoverInvoiceInfoWindow() {
        this.invoiceInfoWindow.toFront();
        this.InvoiceCoverWindow.toFront();
    }

    @FXML
    public void uncoverBankInfoWindow() {
        this.bankInfoWindow.toFront();
    }

    @FXML
    public void uncoverInvoiceInfo() {
        this.invoiceInfoWindow.toFront();
    }

    @FXML
    public void hideInfo() {
        this.infoCoverWindow.toFront();
    }

    @FXML
    public void changeD1Class() {
        d1.getStyleClass().remove(d1.getStyleClass().size()-1);
        d1.getStyleClass().add("buttonSquare");
    }

    @FXML
    public void handled1() {
        setSelectedDeliveryDate(d1.getText());
        confirmDeliveryDate();
        setDeliveryDateText();
    }
    @FXML
    public void handled2() {
        setSelectedDeliveryDate(d2.getText());
        confirmDeliveryDate();
        setDeliveryDateText();
        changeD1Class();
    }
    @FXML
    public void handled3() {
        setSelectedDeliveryDate(d3.getText());
        confirmDeliveryDate();
        setDeliveryDateText();
        changeD1Class();
    }
    @FXML
    public void handled4() {
        setSelectedDeliveryDate(d4.getText());
        confirmDeliveryDate();
        setDeliveryDateText();
        changeD1Class();
    }
    @FXML
    public void handled5() {
        setSelectedDeliveryDate(d5.getText());
        confirmDeliveryDate();
        setDeliveryDateText();
        changeD1Class();
    }
    @FXML
    public void handled6() {
        setSelectedDeliveryDate(d6.getText());
        confirmDeliveryDate();
        setDeliveryDateText();
        changeD1Class();
    }
    @FXML
    public void handled7() {
        setSelectedDeliveryDate(d7.getText());
        confirmDeliveryDate();
        setDeliveryDateText();
        changeD1Class();
    }
    @FXML
    public void handled8() {
        setSelectedDeliveryDate(d8.getText());
        confirmDeliveryDate();
        setDeliveryDateText();
        changeD1Class();
    }
    @FXML
    public void handled9() {
        setSelectedDeliveryDate(d9.getText());
        confirmDeliveryDate();
        setDeliveryDateText();
        changeD1Class();
    }
    @FXML
    public void handled10() {
        setSelectedDeliveryDate(d10.getText());
        confirmDeliveryDate();
        setDeliveryDateText();
        changeD1Class();
    }
    @FXML
    public void handled11() {
        setSelectedDeliveryDate(d11.getText());
        confirmDeliveryDate();
        setDeliveryDateText();
        changeD1Class();
    }

    public void confirmPurchase() {
        BackendController bc = BackendController.getInstance();
        bc.placeOrder();
        addRecieptFromOrder();
        updateFinalWindow();
        openFinalWindow();
        orderIsFinished = true;
    }

    public void populateCurrent() {
        BackendController backendController = BackendController.getInstance();
        Customer c = backendController.getCustomer();
        fNameField.setText(c.getFirstName());
        fNameField2.setText(c.getFirstName());
        lNameField.setText(c.getLastName());
        lNameField2.setText(c.getLastName());
        addressField.setText(c.getAddress());
        addressField2.setText(c.getAddress());
        codeField.setText(c.getPostCode());
        codeField2.setText(c.getPostCode());
        cityField.setText(c.getPostAddress());
        cityField2.setText(c.getPostAddress());
    }

    @Override
    public void addObserver(ConfirmedOrderObserver coo) {
        observers.add(coo);
    }

    @Override
    public void addRecieptFromOrder() {
        BackendController bc = BackendController.getInstance();
        List<Order> orders = bc.getReciepts();
        for (ConfirmedOrderObserver o : observers)
            o.createReciept(orders);
    }
}
