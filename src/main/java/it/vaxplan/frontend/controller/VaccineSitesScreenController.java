package it.vaxplan.frontend.controller;

import it.vaxplan.backend.VaccineSite;
import it.vaxplan.backend.service.VaccineSiteService;
import it.vaxplan.frontend.CampaignToAdd;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class VaccineSitesScreenController implements Initializable {

    @FXML
    public ListView<VaccineSite> availableSitesList;
    @FXML
    public ListView<VaccineSite> selectedSitesList;
    @FXML
    public Button addSiteButton;
    @FXML
    public Button removeSiteButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!VaccineSiteService.isEmpty()) {
            availableSitesList.getItems().addAll(VaccineSiteService.getSites());
        }
    }

    /**
     * Adds a vaccination site selected from the list visible on the left to the list
     * visible on the right
     */
    public void addSite() {
        var toAdd = availableSitesList.getSelectionModel().getSelectedItem();
        selectedSitesList.getItems().add(toAdd);
        availableSitesList.getItems().remove(toAdd);
        selectedSitesList.refresh();
        availableSitesList.refresh();
    }

    /**
     * Removes a vaccination site selected in the second list from the list of
     * selected vaccination sites and move it back to the list on the left
     */
    public void removeSite() {
        var toRemove = selectedSitesList.getSelectionModel().getSelectedItem();
        availableSitesList.getItems().add(toRemove);
        selectedSitesList.getItems().remove(toRemove);
        selectedSitesList.refresh();
        availableSitesList.refresh();
    }

    /**
     * Saves the contents of the list of available vaccination sites (on the right)
     * in a Set and adds it to the current vaccine campaign
     */
    public void confirmButtonAction() {
        Set<VaccineSite> setToAdd = new HashSet<>(selectedSitesList.getItems());
        CampaignToAdd.campaign.setAvailableSites(setToAdd);

        System.out.println(CampaignToAdd.campaign.getAvailableSites());
    }
}
