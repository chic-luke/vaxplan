package it.vaxplan.frontend;

import it.vaxplan.backend.Vaccine;
import it.vaxplan.backend.VaccineCampaign;
import it.vaxplan.backend.service.BookingService;
import it.vaxplan.backend.service.VaccineCampaignService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AddCampaignController implements Initializable {

    @FXML
    public TextField nameTextField;
    @FXML
    public ComboBox<Vaccine> vaccineTypeComboBox;
    @FXML
    public TextField dosesTextField;
    @FXML
    public Button patientCategoriesButton;
    @FXML
    public ComboBox<Integer> dateStartDayBox;
    @FXML
    public ComboBox<Integer> dateStartMonthBox;
    @FXML
    public ComboBox<Integer> dateStartYearBox;
    @FXML
    public ComboBox<Integer> dateEndDayBox;
    @FXML
    public ComboBox<Integer> dateEndMonthBox;
    @FXML
    public ComboBox<Integer> dateEndYearBox;
    @FXML
    public ComboBox<Integer> timeStartHourBox;
    @FXML
    public ComboBox<Integer> timeStartMinutesBox;
    @FXML
    public ComboBox<Integer> timeEndHourBox;
    @FXML
    public ComboBox<Integer> timeEndMinutesBox;
    @FXML
    public Button backButton;
    @FXML
    public Button confirmButton;

    // Values to enter in vaccine campaign
    private String name;
    private Vaccine vaccine;
    private Integer availableDoses;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime dailyStartTime;
    private LocalTime dailyEndTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        var dayRange = Arrays.stream(IntStream.rangeClosed(1, 30).toArray())
                .boxed().collect(Collectors.toList());
        var monthRange = Arrays.stream(IntStream.rangeClosed(1, 12).toArray())
                .boxed().collect(Collectors.toList());
        var yearRange = Arrays.stream(IntStream.rangeClosed(2021, 2069).toArray())
                .boxed().collect(Collectors.toList());
        var hoursRange = Arrays.stream(IntStream.rangeClosed(0, 31).toArray())
                .boxed().collect(Collectors.toList());
        var minutesRange = Arrays.stream(IntStream.rangeClosed(0, 59).toArray())
                .boxed().collect(Collectors.toList());

        // Initialize comboboxes
        dateStartDayBox.getItems().addAll(dayRange);
        dateStartMonthBox.getItems().addAll(monthRange);
        dateStartYearBox.getItems().addAll(yearRange);
        dateEndDayBox.getItems().addAll(dayRange);
        dateEndMonthBox.getItems().addAll(monthRange);
        dateEndYearBox.getItems().addAll(yearRange);
        timeStartHourBox.getItems().addAll(hoursRange);
        timeStartMinutesBox.getItems().addAll(minutesRange);
        timeEndHourBox.getItems().addAll(hoursRange);
        timeEndMinutesBox.getItems().addAll(minutesRange);

        // Initialize Vaccines ComboBox
        vaccineTypeComboBox.getItems().addAll(Vaccine.values());
    }

    public void setName() {
        if (!nameTextField.getText().equals(""))
            CampaignToAdd.setName(nameTextField.getText());
    }

    public void setVaccine() {
        if (!vaccineTypeComboBox.getSelectionModel().isEmpty())
            CampaignToAdd.setVaccine(vaccineTypeComboBox.getValue());
    }

    public void setDoses() {
        if (!dosesTextField.getText().equals(""))
            CampaignToAdd.setAvailableDoses(Integer.parseInt(dosesTextField.getText()));
    }

    public void setDateTime() {
        // Populate fields with user input
        // Values from comboboxes
        var dateStartDay = dateStartDayBox.getValue();
        var dateStartMonth = dateStartMonthBox.getValue();
        var dateStartYear = dateStartYearBox.getValue();
        var dateEndDay = dateEndDayBox.getValue();
        var dateEndMonth = dateEndMonthBox.getValue();
        var dateEndYear = dateEndYearBox.getValue();
        var timeStartHour = timeStartHourBox.getValue();
        var timeStartMinutes = timeStartMinutesBox.getValue();
        var timeEndHour = timeEndHourBox.getValue();
        var timeEndMinutes = timeEndMinutesBox.getValue();

        // Check and set begin and end dates
        if (dateStartYear != null && dateStartMonth != null && dateStartDay != null) {
            CampaignToAdd.setStartDate(LocalDate.of(dateStartYear, dateStartMonth, dateStartDay));
        }

        if (dateEndYear != null && dateEndMonth != null && dateEndDay != null) {
            CampaignToAdd.setEndDate(LocalDate.of(dateEndYear, dateEndMonth, dateEndDay));
        }

        if (CampaignToAdd.getStartDate().isAfter(CampaignToAdd.getEndDate())) {
            throw new IllegalArgumentException();
        }


        // Set and check begin and end times
        if (timeStartHour != null && timeStartMinutes != null) {
            CampaignToAdd.setDailyStartTime(LocalTime.of(timeStartHour, timeStartMinutes));
        }

        if (timeEndHour != null && timeEndMinutes != null) {
            CampaignToAdd.setDailyEndTime(LocalTime.of(timeEndHour, timeEndMinutes));
        }

        if (CampaignToAdd.getDailyStartTime().isAfter(CampaignToAdd.getDailyEndTime())) {
            throw new IllegalArgumentException();
        }

    }

    public void backButtonAction() throws IOException {
        App.setRoot("adminscreen");
    }

    public void moreButtonAction() throws IOException {
        App.setRoot("AddCampaignMore");
    }

    public void confirmButtonAction() throws IOException {
        setName();
        setVaccine();
        setDoses();
        setDateTime();

        var bookings = new BookingService();

        var newCampaign = new VaccineCampaign(CampaignToAdd.getName(), CampaignToAdd.getVaccine(),
                CampaignToAdd.getAvailableDoses(), CampaignToAdd.getStartDate(), CampaignToAdd.getEndDate(),
                CampaignToAdd.getDailyStartTime(), CampaignToAdd.getDailyEndTime(),
                bookings);
        VaccineCampaignService.addCampaign(newCampaign);
        System.out.println("Success!");

        backButtonAction();
    }
}
