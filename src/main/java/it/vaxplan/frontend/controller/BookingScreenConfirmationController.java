package it.vaxplan.frontend.controller;

import it.vaxplan.frontend.App;
import it.vaxplan.frontend.BookingData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookingScreenConfirmationController implements Initializable {

    @FXML
    public Label firstNameLabel;
    @FXML
    public Label lastNameLabel;
    @FXML
    public Label campaignLabel;
    @FXML
    public Label dateLabel;
    @FXML
    public Label hourStartLabel;
    @FXML
    public Label hoursEndLabel;
    @FXML
    public Button endButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstNameLabel.setText(BookingData.getPatient().getFirstName());
        lastNameLabel.setText(BookingData.getPatient().getLastName());
        campaignLabel.setText(BookingData.getCampaign().getName());
        dateLabel.setText(BookingData.getDay().toString());
        hourStartLabel.setText(BookingData.getTime().toString());
        hoursEndLabel.setText(BookingData.getTime().plusMinutes(10).toString());
    }

    public void endButtonAction() throws IOException {
        App.setRoot("welcome");
    }

}
