package it.vaxplan.frontend.controller;

import it.vaxplan.frontend.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationScreenExtraController implements Initializable {

    @FXML
    public Button backButton;
    @FXML
    public Button confirmButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Goes back to the previous view
     * @throws IOException if FXML file is not found
     */
    public void backButtonAction() throws IOException {
        App.setRoot("registrationScreen");
    }

    /**
     * Constructs a Patient object according to the input, adds it to
     * PatientService and then goes back to the welcome screen
     * @throws IOException if FXML file is not found
     */
    public void confirmButtonAction() throws IOException{
        App.setRoot("welcome");
    }

}
