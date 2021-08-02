package it.vaxplan.frontend.controller;

import it.vaxplan.frontend.App;
import it.vaxplan.frontend.CampaignToAdd;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCampaignExtraController implements Initializable {

    @FXML
    public TextField siteTextBox;
    @FXML
    public Button addSiteButton;
    @FXML
    public ComboBox<String> sitesComboBox;
    @FXML
    public Button sitesComboBoxRefreshButton;
    @FXML
    public ComboBox<String> patientCategoriesComboBox;
    @FXML
    public Button patientCategoriesComboBoxRefreshButton;
    @FXML
    public ComboBox<String> categoriesComboBox;
    @FXML
    public Button categoriesComboBoxRefreshButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showSites();
    }

    /**
     * Goes back to the previous screen when the back button is pressed.
     * @throws IOException
     */
    public void backButtonAction() throws IOException {
        App.setRoot("addCampaign");
    }

    /**
     * Adds a new vaccination site according to the contents of the associated TextBox
     */
    public void addSite() {
        if (!siteTextBox.getText().equals("")) {
            CampaignToAdd.campaign.availableSites.add(siteTextBox.getText());
            siteTextBox.clear();
        }
    }

    /**
     * Show a list contaning the currently selected vaccination sites
     */
    public void showSites() {
        if (!CampaignToAdd.campaign.availableSites.isEmpty()) {
            sitesComboBox.getItems().clear();
            sitesComboBox.getItems().addAll(CampaignToAdd.campaign.availableSites);
        }
    }

    /**
     * Go back to the previous view
     * @throws IOException
     */
    public void confirmButtonAction() throws IOException {
        backButtonAction();
    }

}
