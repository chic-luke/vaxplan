package it.vaxplan.frontend.controller;

import it.vaxplan.backend.PatientCategories;
import it.vaxplan.frontend.App;
import it.vaxplan.frontend.CampaignToEdit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EditCampaignScreenController implements Initializable {
    // Doses
    @FXML
    public TextField dosesAddField;

    // Dates and time
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

    // Buttons
    @FXML
    public Button backButton;
    @FXML
    public Button backButton1;
    @FXML
    public Button backButton2;
    @FXML
    public Button backButton3;
    @FXML
    public Button okButton;
    @FXML
    public Button okButton1;
    @FXML
    public Button okButton2;
    @FXML
    public Button okButton3;

    // Patient categories
    @FXML
    public ListView<PatientCategories> availableCategoriesList;
    @FXML
    public ListView<PatientCategories> selectedCategoriesList;
    @FXML
    public Button addCategoryButton;
    @FXML
    public Button removeCategoryButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("UUID DOPO:" + CampaignToEdit.campaign.getUuid());
        System.out.println("Editing:" + CampaignToEdit.campaign);

        var dayRange = Arrays.stream(IntStream.rangeClosed(1, 30).toArray())
                .boxed().collect(Collectors.toList());
        var monthRange = Arrays.stream(IntStream.rangeClosed(1, 12).toArray())
                .boxed().collect(Collectors.toList());
        var yearRange = Arrays.stream(IntStream.rangeClosed(2021, 2069).toArray())
                .boxed().collect(Collectors.toList());
        var hoursRange = Arrays.stream(IntStream.rangeClosed(0, 24).toArray())
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

        // Initialize patient categories ListView
        availableCategoriesList.getItems().addAll(PatientCategories.values());
    }


    /**
     * Goes back to the previous view
     * @throws IOException
     */
    public void backButtonAction() throws IOException {
        App.setRoot("adminscreen");
    }

    /**
     * Add an amount of doses equal to what's specified in the designated text field to the amount of available doses
     * relative to the vaccine campaign which is currently being edited.
     */
    public void addDoses() {
        if (!dosesAddField.getText().equals(""))
            CampaignToEdit.campaign.addDoses(Integer.parseInt(dosesAddField.getText()));
    }

    /**
     * Update the start date, end date, and daily start and end times to the vaccine campaign that is currently being
     * edited according to the values the user has specified in the designated comboboxes.
     */
    public void setDateTime() {
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

        // Daily start time
        if (timeStartHour != null && timeStartMinutes != null) {
            if (CampaignToEdit.campaign.getDailyEndTime() != null &&
                    CampaignToEdit.campaign.getDailyEndTime().isBefore(LocalTime.of(timeStartHour, timeStartMinutes)))
                throw new IllegalArgumentException();
            else
                CampaignToEdit.campaign.setDailyStartTime(LocalTime.of(timeStartHour, timeStartMinutes));
        }
        System.out.println(CampaignToEdit.campaign.getDailyStartTime());

        // Daily end time
        if (timeEndHour != null && timeEndMinutes != null) {
            if (CampaignToEdit.campaign.getDailyStartTime() != null &&
                    CampaignToEdit.campaign.getDailyStartTime().isAfter(LocalTime.of(timeEndHour, timeEndMinutes)))
                throw new IllegalArgumentException();
            else
                CampaignToEdit.campaign.setDailyEndTime(LocalTime.of(timeEndHour, timeEndMinutes));
        }
        System.out.println(CampaignToEdit.campaign.getDailyEndTime());

        // Start date
        if (dateStartDay != null && dateStartMonth != null && dateStartYear != null) {
            if (CampaignToEdit.campaign.getEndDate() != null &&
                    CampaignToEdit.campaign.getEndDate().isBefore(LocalDate.of(dateStartYear, dateStartMonth, dateStartDay)))
                throw new IllegalArgumentException();
            else
                CampaignToEdit.campaign.setStartDate(LocalDate.of(dateStartYear, dateStartMonth, dateStartDay));
        }
        System.out.println(CampaignToEdit.campaign.getStartDate());

        // End date
        if (dateEndDay != null && dateEndMonth != null && dateEndYear != null) {
            if (CampaignToEdit.campaign.getStartDate() != null &&
                    CampaignToEdit.campaign.getStartDate().isAfter(LocalDate.of(dateEndYear, dateEndMonth, dateEndDay)))
                throw new IllegalArgumentException();
            else
                CampaignToEdit.campaign.setEndDate(LocalDate.of(dateEndYear, dateEndMonth, dateEndDay));
        }
        System.out.println(CampaignToEdit.campaign.getEndDate());
    }

    /**
     * This method gets called from the patients categories tab.
     * Adds a category of patients selected from the list visible on the left to the list
     * visiable on the right
     */
    public void addCategory() {
        var toAdd = availableCategoriesList.getSelectionModel().getSelectedItem();
        selectedCategoriesList.getItems().add(toAdd);
        availableCategoriesList.getItems().remove(toAdd);
        selectedCategoriesList.refresh();
        availableCategoriesList.refresh();
    }

    /**
     * This method gets called from the patients categories tab.
     * Removes a category of patient selected in the second list from the list of
     * eligible patient categories and move it back to the list on the left
     */
    public void removeCategory() {
        var toRemove = selectedCategoriesList.getSelectionModel().getSelectedItem();
        availableCategoriesList.getItems().add(toRemove);
        selectedCategoriesList.getItems().remove(toRemove);
        selectedCategoriesList.refresh();
        availableCategoriesList.refresh();
    }

    /**
     * Saves the contents of the list of available patient categories (on the right)
     * in a Set and adds it to the current vaccine campaign
     */
    public void setCategories() {
        Set<PatientCategories> setToAdd = new HashSet<>();
        setToAdd.addAll(selectedCategoriesList.getItems());
        CampaignToEdit.campaign.setPatientCategories(setToAdd);
    }

    /**
     * Processes the inputs of the various objects in this view, adds them to the
     * current VaccineCampaign and then goes back to the preview view
     * @throws IOException
     */
    public void okButtonAction() throws IOException {
        addDoses();
        setDateTime();
        setCategories();

        backButtonAction();
    }
}
