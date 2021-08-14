package it.vaxplan.frontend.controller;

import it.vaxplan.backend.BookingValidation;
import it.vaxplan.backend.VaccineCampaign;
import it.vaxplan.frontend.App;
import it.vaxplan.frontend.CitizenBookingFields;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectCampaignScreenController implements Initializable {

    @FXML
    public Label patientName;
    @FXML
    public ListView<VaccineCampaign> campaignsListView;
    @FXML
    public Button nextButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patientName.setText(CitizenBookingFields.getCurrentPatient().getFirstName() + " " +
                CitizenBookingFields.getCurrentPatient().getLastName());

        campaignsListView.getItems().addAll(BookingValidation.availableCampaignsForPatient(
                CitizenBookingFields.getCurrentPatient()));
    }

    public void nextButtonAction() throws IOException {
        CitizenBookingFields.setSelectedCampaign(campaignsListView.getSelectionModel().getSelectedItem());

        App.setRoot("BookingScreen");
    }

}
