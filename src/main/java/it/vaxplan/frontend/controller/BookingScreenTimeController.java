package it.vaxplan.frontend.controller;

import it.vaxplan.backend.Booking;
import it.vaxplan.backend.VaccineCampaign;
import it.vaxplan.backend.VaccineSite;
import it.vaxplan.backend.json.Sync;
import it.vaxplan.frontend.App;
import it.vaxplan.frontend.BookingData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class BookingScreenTimeController implements Initializable {

    @FXML
    public ListView<LocalDateTime> slotsListView;
    @FXML
    public Button nextButton;

    VaccineCampaign campaign = BookingData.getCampaign();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var slotsList = new ArrayList<LocalDateTime>();

        // Get HashMap connected to current site for current campaign
        HashMap<LocalDateTime, Boolean> currentMap = new HashMap<>();
        for (Map.Entry<VaccineSite, HashMap<LocalDateTime, Boolean>> set :
        campaign.getSlotsPerSite().entrySet()) {
            var site = set.getKey();
            var tmpMap = set.getValue();

            if (site.equals(BookingData.getSite())) {
                currentMap = tmpMap;
            }
        }

        // Iterate through that HashMap to find all items of Date and put them in a list
        for (Map.Entry<LocalDateTime, Boolean> set :
        currentMap.entrySet()) {
            var dateTime = set.getKey();
            var isEligible = set.getValue();

            if (isEligible && dateTime.getDayOfYear() == BookingData.getDay().getDayOfYear()) {
                slotsList.add(dateTime);
            }
        }

        // Sort list of LocalDateTime just obtained
        var sortedSlotsList = slotsList.stream().sorted().collect(Collectors.toList());

        // Finally, display the list of slots we just obtained
        slotsListView.getItems().addAll(sortedSlotsList);

    }

    public void nextButtonAction() throws IOException {
        campaign.bookTimeSlot(slotsListView.getSelectionModel().getSelectedItem(), BookingData.getSite());
        BookingData.setTime(slotsListView.getSelectionModel().getSelectedItem());

        var finalBooking = new Booking(BookingData.getPatient(), BookingData.getCampaign().getUuid(), BookingData.getDay(),
                BookingData.getTime(), BookingData.getSite());

        System.out.println(finalBooking);

        // TEST
        BookingData.getCampaign().setListOfBookings(new ArrayList<>());

        BookingData.getCampaign().addBooking(finalBooking);

        System.out.println(BookingData.getCampaign().getListOfBookings());

        Sync.writeVaccineCampaignServiceToJson();

        App.setRoot("BookingScreenConfirmation");
    }

}
