package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

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



    public MyAccountContactInfo() {
        setRoot();
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
    }

    public void saveFieldsContact() {

    }
}
