package Model;

import Controller.BackendController;
import Controller.MyAccountController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
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
    public CheckBox monday;

    @FXML
    public CheckBox tuesday;

    @FXML
    public CheckBox wednesday;

    @FXML
    public CheckBox thursday;

    @FXML
    public CheckBox friday;

    @FXML
    public CheckBox seven;

    @FXML
    public CheckBox ten;

    @FXML
    public CheckBox one;

    @FXML
    public CheckBox four;

    @FXML
    public CheckBox nineteen;


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
        save.setDisable(true);
        populateCurrent();
        msg.setText("");
        resetMsg();

    }

    public void populateCurrent() {
        BackendController backendController = BackendController.getInstance();
        Customer c = backendController.getCustomer();
        name.setText(c.getFirstName());
        lastname.setText(c.getLastName());
        address.setText(c.getAddress());
        postalcode.setText(c.getPostCode());
        city.setText(c.getPostAddress());
        mail.setText(c.getEmail());
        telnumber.setText(c.getMobilePhoneNumber());

        if (hasNoError())
            save.setDisable(false);

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

    public void ButtonAble() {
        if (hasNoError() && isRightFormatName() && isRightFormatMail())
            save.setDisable(false);
        else
            save.setDisable(true);
    }

    private boolean isRightFormatMail() {
        if (!mail.getText().isEmpty() && (mail.getText().indexOf("@")>=1) && (mail.getText().indexOf(".") >= 2) ){
            errMail.setText("");
            return true;
        }
        else {
            errMail.setText("Ange giltig mailadress");
            return false;
        }
    }

    private boolean isRightFormatName() {
        if (!(name.getText().isEmpty()) && name.getText().length() < 12){
            errName.setText("");
            return true;
        }
        else {
            errName.setText("Max 12 tecken");
            return false;
        }
    }

    private Boolean hasNoError() {
        if(!name.getText().equals("") && !lastname.getText().equals("") && !address.getText().equals("") && !postalcode.getText().equals("") && !mail.getText().equals("") )
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
        save.setDisable(true);
    }

    public void saveFieldsContact() {
        BackendController backendController = BackendController.getInstance();
        Customer c = backendController.getCustomer();
        resetMsg();
        if (!(name.getText().isEmpty()) && name.getText().length() < 12)
            c.setFirstName(name.getText());
        if (!lastname.getText().isEmpty() && lastname.getText().length() < 15)
            c.setLastName(lastname.getText());
        if (!address.getText().isEmpty())
            c.setAddress(address.getText());
         if (!postalcode.getText().isEmpty() || !city.getText().isEmpty()) {
            c.setPostCode(postalcode.getText());
            c.setPostAddress(city.getText());
        }
        if (!mail.getText().isEmpty())
            c.setEmail(mail.getText());
        if (!telnumber.getText().isEmpty())
            c.setMobilePhoneNumber(telnumber.getText());

        if (hasNoError()){

            msg.setText("Uppgifter sparade till " + c.getFirstName() + "s konto.");
        }

    }
}
