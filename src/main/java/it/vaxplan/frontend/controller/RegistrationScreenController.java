package it.vaxplan.frontend.controller;

import it.vaxplan.backend.Patient;
import it.vaxplan.backend.Sex;
import it.vaxplan.backend.exceptions.InvalidFiscalCodeException;
import it.vaxplan.backend.json.Sync;
import it.vaxplan.backend.service.PatientService;
import it.vaxplan.frontend.App;
import it.vaxplan.frontend.BookingData;
import it.vaxplan.frontend.RegistrationData;
import it.vaxplan.frontend.RegistrationFields;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.vaxplan.backend.utils.IDChecker;

public class RegistrationScreenController implements Initializable {

    // User fields
    @FXML
    public TextField nameTextField;
    @FXML
    public TextField surnameTextField;
    @FXML
    public ComboBox<Integer> birthDay;
    @FXML
    public ComboBox<Integer> birthMonth;
    @FXML
    public ComboBox<Integer> birthYear;
    @FXML
    public TextField bornWhere;
    @FXML
    public TextField fiscalCode;
    @FXML
    public TextField healthCardNumberTextField;
    @FXML
    public ComboBox<Sex> sexComboBox;

    // Back and confirm buttons
    @FXML
    public Button backButton;
    @FXML
    public Button nextButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Initialisation range dates
        var dayDates = Arrays.stream(IntStream.rangeClosed(1, 31).toArray())
                .boxed().collect(Collectors.toList());
        var monthDates = Arrays.stream(IntStream.rangeClosed(1, 12).toArray())
                .boxed().collect(Collectors.toList());
        var yearDates = Arrays.stream(IntStream.rangeClosed(Calendar.getInstance().get(Calendar.YEAR) - 120,
                Calendar.getInstance().get(Calendar.YEAR))
                .toArray())
                .boxed().collect(Collectors.toList());

        // Initialize comboboxes
        birthDay.getItems().addAll(dayDates);
        birthMonth.getItems().addAll(monthDates);
        birthYear.getItems().addAll(yearDates);

        // Initialize Sex ComboBox
        sexComboBox.getItems().addAll(Sex.values());

        // Initialize registration fields
        RegistrationData.fields = new RegistrationFields();
    }

    /**
     * Sets the Fiscal Code field acocrding to the contents of the fiscal code TextField,
     * after validating it and confirming it's indeed a valid Italian fiscal code.
     */
    public void setFiscalCode(){
        if (IDChecker.isFiscalCodeValid(fiscalCode.getText()) && !fiscalCode.getText().equals("")) {
            System.out.println("Fiscal code check all right");
            RegistrationData.fields.setFiscalCode(fiscalCode.getText());
        } else {
            throw new InvalidFiscalCodeException();
        }
    }

    public void setFields() {
        if (!nameTextField.getText().equals("")) {
            RegistrationData.fields.setFirstName(nameTextField.getText());
        }

        if (!surnameTextField.getText().equals("")) {
            RegistrationData.fields.setLastName(surnameTextField.getText());
        }

        if (!healthCardNumberTextField.getText().equals("")) {
            RegistrationData.fields.setHealthCardNumber(healthCardNumberTextField.getText());
        }

        if (birthDay.getValue() != null && birthMonth.getValue() != null && birthYear.getValue() != null) {
            RegistrationData.fields.setBirthDay(
                    LocalDate.of(birthYear.getValue(), birthMonth.getValue(), birthDay.getValue()));
        }

        if (!bornWhere.getText().equals("")) {
            RegistrationData.fields.setBirthPlace(bornWhere.getText());
        }

        if (sexComboBox.getValue() != null) {
            RegistrationData.fields.setSex(sexComboBox.getValue());
        }
    }

    /**
     * Goes back to the welcome screen
     * @throws IOException if FXML file is not found
     */
    public void backButtonAction() throws IOException {
        App.setRoot("welcome");
    }

    /**
     * Saves the current user input and then proceeds to the next view
     * @throws IOException if FXML file is not found
     */
    public void confirmButtonAction() throws IOException {
        // Grab inputs
        setFields();
        setFiscalCode();

        // Create temporary patient to check against (DO NOT REGISTER THIS OBJECT)
        var tmpPatient = Patient.builder()
                .firstName(RegistrationData.fields.getFirstName())
                .lastName(RegistrationData.fields.getLastName())
                .fiscalCode(RegistrationData.fields.getFiscalCode())
                .healthCardNumber(RegistrationData.fields.getHealthCardNumber())
                .birthPlace(RegistrationData.fields.getBirthPlace())
                .birthDay(RegistrationData.fields.getBirthDay())
                .sex(RegistrationData.fields.getSex())
                .build();

        // If currently input information matches with an actual citizen, add that citizen to the set of
        // patients
        if (IDChecker.isRegisterDataCorrect(tmpPatient)) {
            var patient = IDChecker.getCitizen(RegistrationData.fields.getFiscalCode());

            // Print out PatientService for debug
            System.out.println("PatientService: " + PatientService.getPatients());

            if (IDChecker.isCitizenRegistered(patient.getFiscalCode())) {
                System.out.println("Patient already registered! Reusing");
            } else {
                // Add patient to PatientService
                PatientService.addPatient(patient);
            }

            // Print out PatientService for debug
            System.out.println("PatientService: " + PatientService.getPatients());

            // Sync to JSON file
            Sync.writePatientServiceToJson();

            // Save the current patient in the designated singleton for next view
            BookingData.setPatient(patient);
            RegistrationData.registeredPatient = patient;

            // Go to registration confirmation screen
            App.setRoot("registrationSecondScreen");
        } else {
            App.setRoot("ErrorScreen");
        }

    }

}
