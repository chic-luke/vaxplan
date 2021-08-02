package it.vaxplan.frontend.controller;

import it.vaxplan.backend.exceptions.InvalidFiscalCodeException;
import it.vaxplan.frontend.App;
import it.vaxplan.frontend.RegistrationFieldsSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.vaxplan.backend.IDChecker;

public class RegistrationScreenController implements Initializable {

    //User fields
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

    //back and confirm button
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
    }

    public void setFiscalCode(){
        if (IDChecker.isFiscalCodeValid(fiscalCode.getText()) && !fiscalCode.getText().equals("")) {
            System.out.println("Fiscal code check all right");
            RegistrationFieldsSingleton.registrationFields.setFiscalCode(fiscalCode.getText());
        } else {
            throw new InvalidFiscalCodeException();
        }
    }

    public void backButtonAction() throws IOException {
        App.setRoot("welcome");
    }

    public void confirmButtonAction() throws  IOException{
        App.setRoot("registrationSecondScreen");
    }
}
