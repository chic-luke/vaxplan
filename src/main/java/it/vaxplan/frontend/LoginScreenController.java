package it.vaxplan.frontend;

import it.vaxplan.frontend.LoginScreen;

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
            LoginScreen.setRoot("adminscreen");
        else if (IDChecker.isIDRegistered(inputString))
            LoginScreen.setRoot("citizenscreen");
        else
            LoginScreen.setRoot("registrationscreen");

    }
}
