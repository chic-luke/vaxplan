package it.vaxplan.frontend.controller;
import it.vaxplan.frontend.CitizenBookingFields;

import it.vaxplan.backend.VaccineCampaign;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BookingScreenController implements Initializable {

    //combobox initialization
    @FXML
    public ComboBox<Integer> vacDay;
    @FXML
    public ComboBox<Integer> vacMonth;
    @FXML
    public ComboBox<Integer> vacYear;
    @FXML
    public ComboBox<Integer> hour;
    @FXML
    public ComboBox<Integer> minutes;

    //ListView initialization
    @FXML
    public ListView<VaccineCampaign> aviableSites;

    VaccineCampaign campaign = CitizenBookingFields.getSelectedCampaign();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDate();
    }

    private void setDate()
    {
        var dayDates = Arrays.stream(IntStream.rangeClosed(1, 31).toArray())
                .boxed().collect(Collectors.toList());
        var monthDates = Arrays.stream(IntStream.rangeClosed(1, 12).toArray())
                .boxed().collect(Collectors.toList());
        var yearDates = Arrays.stream(IntStream.rangeClosed(campaign.getStartDate().getYear(), campaign.getEndDate().getYear()).toArray())
                .boxed().collect(Collectors.toList());

        vacDay.getItems().addAll(dayDates);
        vacMonth.getItems().addAll(monthDates);
        vacYear.getItems().addAll(yearDates);
    }
}
