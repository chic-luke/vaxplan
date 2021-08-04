package it.vaxplan.frontend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.vaxplan.backend.json.Sync;
import it.vaxplan.backend.utils.IDChecker;
import it.vaxplan.frontend.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lombok.SneakyThrows;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {

    @FXML
    public TextField idField;
    @FXML
    public Button loginButton;

    /**
     * Handles input to the login text field.
     * If it contains the word "admin", it loads the administration view.
     * If it contains a valid fiscal code belonging to a patient registered to the system,
     * it loads the citizen view.
     * If it contains an invalid fiscal code, it prompts the user to register themselves
     * to the system.
     * @throws IOException If XML file is not found
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

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            syncData();
        } catch (Exception e) {
            System.out.println("Empty set");
        }

    }

    /**
     * Read data from JSON files into Java data structures
     */
    public void syncData() throws JsonProcessingException {
        // Sync Vaccine Sites
        Sync.initVaccineSiteServiceFromJson();

        // Sync Vaccine campaigns
        Sync.initVaccineCampaignServiceFromJson();

        // Sync patient database
        Sync.initPatientServiceFromJson();

        // Sync bookings
        Sync.initBookingServiceFromJson();
    }
}
