package it.vaxplan.frontend.controller;

import it.vaxplan.frontend.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import it.vaxplan.backend.IDChecker;

public class LoginScreenController {
    public TextField idField;
    public Button loginButton;

    /**
     * Handles input to the login text field.
     * If it contains the word "admin", it loads the administration view.
     * If it contains a valid fiscal code belonging to a patient registered to the system,
     * it loads the citizen view.
     * If it contains an invalid fiscal code, it prompts the user to register themselves
     * to the system.
     * @throws IOException
     */
    @FXML
    private void checkLogin() throws IOException {
        String inputString = idField.getText();

        if (inputString.equals("admin"))
            App.setRoot("adminscreen");
        else if (IDChecker.isIDRegistered(inputString))
            App.setRoot("citizenScreen");
        else
            App.setRoot("registrationScreen");
    }
}
