package it.vaxplan.frontend.controller;

import it.vaxplan.backend.VaccineSite;
import it.vaxplan.frontend.App;
import it.vaxplan.frontend.BookingData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookingScreenSiteController implements Initializable {

    @FXML
    public ListView<VaccineSite> sitesList;
    @FXML
    public Button nextButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var campaign = BookingData.getCampaign();
        var sitesSetToList = new ArrayList<VaccineSite>(campaign.getAvailableSites());
        sitesList.getItems().addAll(sitesSetToList);
    }

    public void nextButtonAction() throws IOException {
        BookingData.setSite(sitesList.getSelectionModel().getSelectedItem());
        App.setRoot("BookingScreenSlot");
    }

}
