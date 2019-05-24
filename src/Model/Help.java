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

    @FXML
    public Text errName;

    @FXML
    public Text errMail;

    @FXML
    public Text errSubject;

    @FXML
    public Text errMsg;


    public Help() {
        setRoot();
        populateFields();
        clearErrMsg();
    }


    private void clearErrMsg() {
        errMsg.setText("");
        errSubject.setText("");
        errMail.setText("");
        errName.setText("");
        msg.setText("");
    }

    public void populateFields() {
        BackendController backendController = BackendController.getInstance();
        Customer c = backendController.getCustomer();
        nameInput.setPromptText("Fyll i namn");
        nameInput.setText(c.getFirstName());
        mailInput.setPromptText("Fyll i en giltig mailadress");
        mailInput.setText(c.getEmail());
        subject.setPromptText("Fyll i ett ämne");
        message.setPromptText("Fyll i en fråga här.");
        msg.setText("");
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
            errName.setText("Namn saknas!");
            i++;
        }
        if (mailInput.getText().isEmpty()) {
            errMail.setText("Epost saknas!");
            i++;
        }
        if (subject.getText().isEmpty()) {
            errSubject.setText("Ämne saknas!");
            i++;
        }
        if (message.getText().isEmpty()){
            errMsg.setText("Fråga saknas!");
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
