package it.vaxplan.frontend.controller;

import it.vaxplan.backend.PatientCategories;
import it.vaxplan.frontend.App;
import it.vaxplan.frontend.RegistrationData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationScreenExtraController implements Initializable {

    @FXML
    public ListView<PatientCategories> categoriesList;
    @FXML
    public Button confirmButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (RegistrationData.registeredPatient.isAtHighRisk()) {
            System.out.println("HIGH RISK");
        }

        var patient = RegistrationData.registeredPatient;

        if (patient.isAtHighRisk())
            categoriesList.getItems().add(PatientCategories.AT_HIGH_RISK);

        if (patient.isHealthCareWorker())
            categoriesList.getItems().add(PatientCategories.HEALTH_WORKER);

        if (patient.isSchoolWorker())
            categoriesList.getItems().add(PatientCategories.SCHOOL_WORKER);

        if (patient.isLawEnforcementWorker())
            categoriesList.getItems().add(PatientCategories.LAW_ENFORCEMENT_WORKER);

        if (patient.isCaretaker())
            categoriesList.getItems().add(PatientCategories.CARETAKER);

        if (patient.isCohabiting())
            categoriesList.getItems().add(PatientCategories.COHABITING);

    }

    /**
     * Constructs a Patient object according to the input, adds it to
     * PatientService and then goes back to the welcome screen
     * @throws IOException if FXML file is not found
     */
    public void confirmButtonAction() throws IOException{
        // Vai alla schermata del cittadino

        App.setRoot("welcome");
    }

}
