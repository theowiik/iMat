package Model;

import Controller.BackendController;
import Controller.MyAccountController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Customer;

import java.awt.*;
import java.io.IOException;

public class MyAccountContactInfo extends AnchorPane implements CustomComponent {

    @FXML
    public javafx.scene.control.TextField name;

    @FXML
    public javafx.scene.control.TextField lastname;

    @FXML
    public javafx.scene.control.TextField address;

    @FXML
    public javafx.scene.control.TextField postalcode;

    @FXML
    public javafx.scene.control.TextField city;

    @FXML
    public javafx.scene.control.TextField telnumber;

    @FXML
    public javafx.scene.control.TextField mail;

    @FXML
    public RadioButton monday;

    @FXML
    public RadioButton tuesday;

    @FXML
    public RadioButton wednesday;

    @FXML
    public RadioButton thursday;

    @FXML
    public RadioButton friday;

    @FXML
    public RadioButton seven;

    @FXML
    public RadioButton ten;

    @FXML
    public RadioButton one;

    @FXML
    public RadioButton four;

    @FXML
    public RadioButton nineteen;


    @FXML
    public javafx.scene.control.Button clear;

    @FXML
    public javafx.scene.control.Button save;

    @FXML
    public Text msg;

    @FXML
    public Text errName;
    @FXML
    public Text errLastName;
    @FXML
    public Text errAddress;
    @FXML
    public Text errPostalCity;
    @FXML
    public Text errMail;
    @FXML
    public Text errTelNumber;


    public MyAccountContactInfo() {
        setRoot();
        populateCurrent();
        msg.setText("");
        resetMsg();
    }

    private void populateCurrent() {
        BackendController backendController = BackendController.getInstance();
        Customer c = backendController.getCustomer();
        name.setText(c.getFirstName());
        lastname.setText(c.getLastName());
        address.setText(c.getAddress());
        postalcode.setText(c.getPostCode());
        city.setText(c.getPostAddress());
        mail.setText(c.getEmail());
        telnumber.setText(c.getMobilePhoneNumber());
    }

    private void resetMsg(){
        errName.setText("");
        errLastName.setText("");
        errAddress.setText("");
        errPostalCity.setText("");
        errMail.setText("");
        errTelNumber.setText("");

    }

    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myAccountContactInfo.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Boolean hasNoError() {
        if(!name.getText().equals("") && !lastname.getText().equals("") && !address.getText().equals("") && !postalcode.getText().equals("") && !mail.getText().equals("") && !telnumber.getText().equals(""))
            return true;

        return false;
    }

    public void clearFieldsContact() {
        name.setText("");
        lastname.setText("");
        address.setText("");
        postalcode.setText("");
        city.setText("");
        telnumber.setText("");
        mail.setText("");
        monday.setSelected(false);
        tuesday.setSelected(false);
        wednesday.setSelected(false);
        thursday.setSelected(false);
        friday.setSelected(false);
        seven.setSelected(false);
        ten.setSelected(false);
        one.setSelected(false);
        four.setSelected(false);
        nineteen.setSelected(false);
        resetMsg();
    }

    public void saveFieldsContact() {
        BackendController backendController = BackendController.getInstance();
        Customer c = backendController.getCustomer();
        resetMsg();
        if (!(name.getText().isEmpty()))
            c.setFirstName(name.getText());
        else
            errName.setText("Förnamn saknas");

        if (!lastname.getText().isEmpty())
            c.setLastName(lastname.getText());
        else
            errLastName.setText("Efternamn saknas");

        if (!address.getText().isEmpty())
            c.setAddress(address.getText());
        else
            errAddress.setText("Adress saknas");

        if (!postalcode.getText().isEmpty() || !city.getText().isEmpty()) {
            c.setPostCode(postalcode.getText());
            c.setPostAddress(city.getText());
        }
        else
            errPostalCity.setText("Postkod & stad saknas");

        if (!mail.getText().isEmpty())
            c.setEmail(mail.getText());
        else
            errMail.setText("Mail saknas");

        if (!telnumber.getText().isEmpty())
            c.setMobilePhoneNumber(telnumber.getText());
        else
            errTelNumber.setText("Telefonnummer saknas");

        if (hasNoError()){

            msg.setText("Uppgifter sparade till " + c.getFirstName() + "s konto.");
        }

    }
}
