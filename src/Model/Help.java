package Model;

import Controller.BackendController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Customer;

import java.io.IOException;

public class Help extends AnchorPane implements CustomComponent {

    @FXML
    public TextField nameInput;

    @FXML
    public TextField mailInput;

    @FXML
    public TextField subject;

    @FXML
    public TextArea message;

    @FXML
    public Text msg;


    public Help() {
        setRoot();
        populateFields();
        msg.setText("");

    }

    public void populateFields() {
        BackendController backendController = BackendController.getInstance();
        Customer c = backendController.getCustomer();
        nameInput.setText(c.getFirstName());
        mailInput.setText(c.getEmail());
    }

    public void send() {
        if (isSuccesful()){
            msg.setText("Ditt meddelande har skickats!");
            subject.setText("");
            message.setText("");
        }
    }

    private boolean isSuccesful() {
        int i = 0;
        if (nameInput.getText().isEmpty()){
            //TODO add error message
            nameInput.setPromptText("Fyll i namn");
            i++;
        }
        if (mailInput.getText().isEmpty()) {
            //TODO add error message
            mailInput.setPromptText("Fyll i en giltig mailadress");
            i++;
        }
        if (subject.getText().isEmpty()) {
            //TODO add error message
            subject.setPromptText("Fyll i ett ämne");
            i++;
        }
        if (message.getText().isEmpty()){
            //TODO add error message
            message.setPromptText("Fyll i en fråga här.");
            i++;
        }
        if (i!=0) {
            msg.setText("Du måste åtgärda " + i + " fel.");
            return false;
        }
        return true;
    }

    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("help.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
