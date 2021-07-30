package it.vaxplan.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import it.vaxplan.backend.IDChecker;

public class LoginScreenController {
    public TextField idField;
    public Button loginButton;

    @FXML
    private void checkLogin(ActionEvent actionEvent) throws IOException {
        String inputString = idField.getText();

        if (inputString.equals("admin"))
            App.setRoot("adminscreen");
        /*else if (IDChecker.isIDRegistered(inputString))
            App.setRoot("citizenscreen");
         */
        else if(inputString.equals("GVNGRN85D16F839W"))
            App.setRoot("citizenScreen");
        else
            App.setRoot("registrationscreen");
    }
}
