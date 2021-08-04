package it.vaxplan.frontend.controller;

import it.vaxplan.backend.Vaccine;
import it.vaxplan.backend.VaccineCampaign;
import it.vaxplan.backend.json.Json;
import it.vaxplan.backend.json.Sync;
import it.vaxplan.backend.service.BookingService;
import it.vaxplan.backend.service.VaccineCampaignService;
import it.vaxplan.frontend.App;
import it.vaxplan.frontend.CampaignToAdd;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        var dayRange = Arrays.stream(IntStream.rangeClosed(1, 30).toArray())
                .boxed().collect(Collectors.toList());
        var monthRange = Arrays.stream(IntStream.rangeClosed(1, 12).toArray())
                .boxed().collect(Collectors.toList());
        var yearRange = Arrays.stream(IntStream.rangeClosed(2021, 2069).toArray())
                .boxed().collect(Collectors.toList());
        var hoursRange = Arrays.stream(IntStream.rangeClosed(0, 23).toArray())
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

    /**
     * Set the name of a vaccine according to the contents of the name TextField
     */
    public void setName() {
        if (!nameTextField.getText().equals(""))
            CampaignToAdd.campaign.setName(nameTextField.getText());
    }

    /**
     * Sets the vaccine type according to the selection in the vaccine ComboBox
     */
    public void setVaccine() {
        if (!vaccineTypeComboBox.getSelectionModel().isEmpty())
            CampaignToAdd.campaign.setVaccine(vaccineTypeComboBox.getValue());
    }

    /**
     * Sets the number of doses available for a vaccine according to the contents
     * of the doses TextField
     */
    public void setDoses() {
        if (!dosesTextField.getText().equals(""))
            CampaignToAdd.campaign.setAvailableDoses(Integer.parseInt(dosesTextField.getText()));
    }

    /**
     * Set the vaccination campaign's start and end dates as well as the daily start
     * and end times according to the selection in the associated ComboBoxes
     */
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
            CampaignToAdd.campaign.setStartDate(LocalDate.of(dateStartYear, dateStartMonth, dateStartDay));
        }

        if (dateEndYear != null && dateEndMonth != null && dateEndDay != null) {
            CampaignToAdd.campaign.setEndDate(LocalDate.of(dateEndYear, dateEndMonth, dateEndDay));
        }

            if (CampaignToAdd.campaign.getEndDate() != null && CampaignToAdd.campaign.getStartDate() != null &&
                    CampaignToAdd.campaign.getStartDate().isAfter(CampaignToAdd.campaign.getEndDate())) {
                throw new IllegalArgumentException();
            }


        // Set and check begin and end times
        if (timeStartHour != null && timeStartMinutes != null) {
            CampaignToAdd.campaign.setDailyStartTime(LocalTime.of(timeStartHour, timeStartMinutes));
        }

        if (timeEndHour != null && timeEndMinutes != null) {
            CampaignToAdd.campaign.setDailyEndTime(LocalTime.of(timeEndHour, timeEndMinutes));
        }

        if (CampaignToAdd.campaign.getDailyStartTime() != null && CampaignToAdd.campaign.getDailyEndTime() != null &&
                CampaignToAdd.campaign.getDailyStartTime().isAfter(CampaignToAdd.campaign.getDailyEndTime())) {
            throw new IllegalArgumentException();
        }

    }

    /**
     * Goes back to the previous screen when the back button is pressed.
     * @throws IOException if FXML file is not found
     */
    public void backButtonAction() throws IOException {
        App.setRoot("adminscreen");
    }

    /**
     * Show the view where the operator can select what vaccination sites this
     * campaign is available in, after grabbing inputs from the current view and
     * memorizing them
     * @throws IOException if FXML file is not found
     */
    public void showSitesScreen() throws IOException {
        setName();
        setVaccine();
        setDoses();
        setDateTime();
        App.newWindow("vaccinationSitesScreen");
    }

    /**
     * Opens a new window containing the view where the operator can choose what
     * categories of patients are eligible for this specific campaign
     * @throws IOException if FXML file is not found
     */
    public void showCategoriesScreen() throws IOException {
        App.newWindow("categoriesScreen");
    }

    /**
     * Create a new VaccineCampaign object, add it to the VaccinationCampaignService
     * and go back to the previous view
     * @throws IOException if FXML file is not found
     */
    public void confirmButtonAction() throws IOException {
        setName();
        setVaccine();
        setDoses();
        setDateTime();

        var newCampaign = new VaccineCampaign(CampaignToAdd.campaign.getName(), CampaignToAdd.campaign.getVaccine(),
                CampaignToAdd.campaign.getAvailableDoses(), CampaignToAdd.campaign.getStartDate(), CampaignToAdd.campaign.getEndDate(),
                CampaignToAdd.campaign.getDailyStartTime(), CampaignToAdd.campaign.getDailyEndTime(),
                CampaignToAdd.campaign.getAvailableSites(),
                CampaignToAdd.campaign.getPatientCategories());
        VaccineCampaignService.addCampaign(newCampaign);

        // Write all vaccine campaigns to file
        Sync.writeVaccineCampaignServiceToJson();

        backButtonAction();
    }
}
