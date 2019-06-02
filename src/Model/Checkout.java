package Model;

import Controller.BackendController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
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
    public TextField phoneNumberField;
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
    public TextField cardField1;
    @FXML
    public TextField cardField2;
    @FXML
    public TextField cardField3;
    @FXML
    public TextField monthYear;
    @FXML
    public TextField monthYear1;
    @FXML
    public TextField cvc;

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
    public Button buyMoreButton;
    @FXML
    public Button buyMoreButton1;
    @FXML
    public Button buyMoreButton2;
    @FXML
    public Button buyMoreButton3;
    @FXML
    public Button buyMoreButton4;
    @FXML
    public Button sparaSomInköpslista;

    @FXML
    public Button paybtn;
    @FXML
    public Button nextStepCart;
    @FXML
    public Button invoiceBtn;
    @FXML
    public Button payWithCardBtn;
    @FXML
    public Button atArrivalBtn;


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
    public Label nameLabel;
    @FXML
    public Label lastNameLabel;
    @FXML
    public Label addressLabel;
    @FXML
    public Label codeLabel;
    @FXML
    public Label cityLabel;
    @FXML
    public Label cardLabel;
    @FXML
    public Label myLabel;
    @FXML
    public Label cvcLabel;
    @FXML
    public Label codeLabel2;

    @FXML
    public AnchorPane indicatorArea;
    public WizardView wizardView = new WizardView(1);

    @FXML
    public FlowPane cartPane;

    Boolean payWithCard = true;
    Boolean invoice = false;
    Boolean payAtArrival = false;

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

    public void responsiveEnabling(){

        checkFormatName();
        checkFormatLastName();
        checkFormatAddress();
        checkFormatCode();
        checkFormatCity();
        if (payWithCard){
            checkFormatBank();
            checkFormatCard();
            checkFormatMmYy();
            checkFormatCvc();
        }
        if (invoice)
            checkNumericField(codeField2,codeLabel2,"Postkod", 5);

        if (hasNoEmptyFields())
            paybtn.setDisable(false);
        else
            paybtn.setDisable(true);

    }

    private Boolean hasNoEmptyFields() {

        if (payWithCard){
            if (checkFormatName() && checkFormatLastName() && checkFormatAddress() && checkFormatCode() &&
                    checkFormatCity() && checkFormatBank() && checkFormatCard() && checkFormatMmYy() && checkFormatCvc()) {
                return true;
            }
        }
        if (payAtArrival){
            if (checkFormatName() && checkFormatLastName() && checkFormatAddress() && checkFormatCode() &&
                    checkFormatCity()) {
                return true;
            }
        }if (invoice){
            if (checkFormatName() && checkFormatLastName() && checkFormatAddress() && checkFormatCode() &&
                    checkFormatCity() && !(fNameField2.getText().isEmpty()) && !(lNameField2.getText().isEmpty()) &&
                    !(addressField2.getText().isEmpty()) && !(codeField2.getText().isEmpty()) && !(cityField2.getText().isEmpty())) {
                return true;
            }
        }
            return false;


    }
    private Boolean checkFormatName() {
        if (!(fNameField.getText().isEmpty())){
            if (fNameField.getText().length() > 12) {
                nameLabel.setText("Förnamn* (Max 12 tecken)");
                nameLabel.setStyle("-fx-text-fill: red");
                fNameField.setStyle("-fx-border-color: red");
                return false;
            }
            nameLabel.setText("Förnamn*");
            nameLabel.setStyle("-fx-text-fill: black");
            fNameField.setStyle("-fx-border-color: grey");
            return true;
        }
        else {
            fNameField.setTooltip(new Tooltip("Ange förnamn, max 12 tecken"));

            return false;
        }
    }
    private Boolean checkFormatLastName() {
        if (!(lNameField.getText().isEmpty())){
            if (lNameField.getText().length() > 15) {
                lastNameLabel.setText("Efternamn* (Max 15 tecken)");
                lastNameLabel.setStyle("-fx-text-fill: red");
                lNameField.setStyle("-fx-border-color: red");
                return false;
            }
            lastNameLabel.setText("Efternamn*");
            lastNameLabel.setStyle("-fx-text-fill: black");
            lNameField.setStyle("-fx-border-color: grey");
            return true;
        }else {
            lNameField.setTooltip(new Tooltip("Ange efternamn, max 15 tecken"));
            return false;
        }
    }

    private Boolean checkFormatAddress() {
        if (!(addressField.getText().isEmpty())){
            return true;
        }else {
            addressField.setTooltip(new Tooltip("Ange din adress"));
            return false;
        }
    }

    private Boolean checkFormatCode() {
        if (!(codeField.getText().isEmpty())){
            checkNumericField(codeField,codeLabel,"Postkod",5);
            return true;

        }else {
            codeField.setTooltip(new Tooltip("Ange din postkod"));
            return false;
        }
    }

    private Boolean checkFormatCity() {
        if (!(cityField.getText().isEmpty())){
            return true;
        }else {
            cityField.setTooltip(new Tooltip("Ange din ort"));
            return false;
        }
    }

    private Boolean checkFormatBank() {
        if (!(bankField.getText().isEmpty())){
            return true;
        }else {
            bankField.setTooltip(new Tooltip("Ange din bank"));
            return false;
        }
    }

    private Boolean checkFormatCard() {
        if (!(cardField.getText().isEmpty()))
            checkNumericField(cardField,cardLabel,"Kort",4);
        if (!(cardField1.getText().isEmpty()))
            checkNumericField(cardField1,cardLabel,"Kort",4);
        if (!(cardField2.getText().isEmpty()))
            checkNumericField(cardField2,cardLabel,"Kort",4);
        if (!(cardField3.getText().isEmpty()))
            checkNumericField(cardField3,cardLabel,"Kort",4);

        if (!(cardField.getText().isEmpty()) && !(cardField1.getText().isEmpty()) && !(cardField2.getText().isEmpty()) &&
                !(cardField3.getText().isEmpty())){
            return true;

        }else {
            cardField.setTooltip(new Tooltip("Ange ditt kortnummer"));
            return false;
        }
    }

    private Boolean checkFormatMmYy(){
        if (!(monthYear.getText().isEmpty())){
            checkNumericField(monthYear,myLabel,"Månad/År",2);
        }
        if (!(monthYear1.getText().isEmpty())){
            checkNumericField(monthYear1,myLabel,"Månad/År",2);
        }
        if (!(monthYear.getText().isEmpty()) && !(monthYear1.getText().isEmpty())){
            return true;
        }else {
            bankField.setTooltip(new Tooltip("Ange månad och datum"));
            return false;
        }
    }

    private Boolean checkFormatCvc() {
        if (!(cvc.getText().isEmpty())){
            checkNumericField(cvc,cvcLabel,"CVC",3);
            return true;

        }else {
            codeField.setTooltip(new Tooltip("Ange CVC"));
            return false;
        }
    }

    private void checkNumericField(TextField field, Label text,String label, int length){
        String string = field.getText();
        //int i = string.length();
        boolean numeric = true;

        try {
            Double num = Double.parseDouble(string);
        } catch (NumberFormatException e) {
            numeric = false;
        }

        if(numeric) {
            /*if (i < length){*/
                text.setText(label);
                text.setStyle("-fx-text-fill: black");
                field.setStyle("-fx-border-color: gray");
          /*  } else {
                field.setText(string.substring(0,i-1));

            }*/
        }
        else {
            text.setText(label + " (Ange bara " + length + " numeriska tecken)");
            text.setStyle("-fx-text-fill: red");
            field.setStyle("-fx-border-color: red");
        }
    }

    public Checkout(MyAccountReciept mar) {
        setRoot();
        addObserver(mar);
        spawnIndicator();
        setBtnFocus();
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
        checkIfItemsInCart();
    }

    private void checkIfItemsInCart() {
        BackendController bg = BackendController.getInstance();
        if (bg.getShoppingCartAmount() > 0)
            nextStepCart.setDisable(false);
        else
            nextStepCart.setDisable(true);

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
        paybtn.setDisable(true);
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
        invoice = true;
        payAtArrival = false;
        payWithCard = false;
        setBtnFocus();
    }

    @FXML
    public void uncoverBankInfoWindow() {
/*        updateButton(bankButton, true);
        updateButton(nextButton, false);
        updateButton(last, false);*/
        this.bankInfoWindow.toFront();
        invoice = false;
        payAtArrival = false;
        payWithCard = true;
        setBtnFocus();
    }



    @FXML
    public void uncoverInvoiceInfo() {
        this.invoiceInfoWindow.toFront();

    }

    @FXML
    public void hideInfo() {
        this.infoCoverWindow.toFront();
        invoice = false;
        payAtArrival = true;
        payWithCard = false;
        setBtnFocus();
    }

    private void setBtnFocus() {
        invoiceBtn.setStyle("-fx-background-color: #D1D0C8");
        atArrivalBtn.setStyle("-fx-background-color: #D1D0C8");
        payWithCardBtn.setStyle("-fx-background-color: #D1D0C8");
        if (invoice)
            invoiceBtn.setStyle("-fx-background-color: grey");
        if (payWithCard)
            payWithCardBtn.setStyle("-fx-background-color: grey");
        if (payAtArrival)
            atArrivalBtn.setStyle("-fx-background-color: grey");

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
        updateAccountInfo();
        orderIsFinished = true;
    }

    private void updateAccountInfo() {
        BackendController backendController = BackendController.getInstance();
        Customer customer = backendController.getCustomer();
        customer.setAddress(addressField.getText());
        customer.setFirstName(fNameField.getText());
        customer.setLastName(lNameField.getText());
        customer.setPostCode(codeField.getText());
        customer.setPostAddress(cityField.getText());
        customer.setMobilePhoneNumber(phoneNumberField.getText());
    }

    public void populateCurrent() {
        BackendController backendController = BackendController.getInstance();
        Customer c = backendController.getCustomer();
        fNameField.setText(c.getFirstName());
        fNameField2.setText(c.getFirstName());
        lNameField.setText(c.getLastName());
        lNameField2.setText(c.getLastName());
        phoneNumberField.setText(c.getMobilePhoneNumber());
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
