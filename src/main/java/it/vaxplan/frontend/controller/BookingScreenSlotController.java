package it.vaxplan.frontend.controller;

import it.vaxplan.backend.Booking;
import it.vaxplan.frontend.App;
import it.vaxplan.frontend.BookingData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookingScreenSlotController implements Initializable {

    @FXML
    public ListView<LocalDate> datesListView;
    @FXML
    public Button nextButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var campaign = BookingData.getCampaign();
        var daysList = new ArrayList<LocalDate>();

        for (LocalDate date = campaign.getStartDate(); date.isBefore(campaign.getEndDate()); date = date.plusDays(1)) {
            daysList.add(date);
        }

        datesListView.getItems().addAll(daysList);
    }

    public void nextButtonAction() throws IOException {
        BookingData.setDay(datesListView.getSelectionModel().getSelectedItem());

        var test = "Patient: " + BookingData.getPatient().getFirstName() + BookingData.getPatient().getLastName() + "\n" +
                "Campaign: " + BookingData.getCampaign() + "\n" +
                "Site: " + BookingData.getSite() + "\n" +
                "Day: " + BookingData.getDay() + "\n" +
                "Time: " + BookingData.getTime() + "\n";

        System.out.println(test);

        App.setRoot("BookingScreenTime");
    }

}
