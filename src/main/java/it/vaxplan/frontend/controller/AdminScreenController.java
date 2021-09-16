package it.vaxplan.frontend.controller;

import it.vaxplan.backend.VaccineCampaign;
import it.vaxplan.backend.service.VaccineCampaignService;
import it.vaxplan.frontend.App;
import it.vaxplan.frontend.CampaignToAdd;
import it.vaxplan.frontend.CampaignToAddFields;
import it.vaxplan.frontend.CampaignToEdit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminScreenController implements Initializable {

    @FXML
    public ComboBox<String> campaignsBox;
    @FXML
    public Button cmpsOK;
    @FXML
    public Button logoutButton;
    @FXML
    public Button newCampaignButton;

    private final List<String> tmpList = new LinkedList<>();

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var vaccineCampaigns = VaccineCampaignService.getCampaigns();

        tmpList.clear();
        for (VaccineCampaign c: vaccineCampaigns) {
            tmpList.add(c.getName());
        }

        System.out.println(tmpList);
        System.out.println("VaccineCampaignService: " + VaccineCampaignService.getCampaigns());

        campaignsBox.setPromptText("Seleziona campagna vaccinale");
        clearComboBox();
        campaignsBox.getItems().addAll(tmpList);
    }


    /**
     * Opens the view to edit the selected vaccine campaign to the currently
     * selected campaign in the ComboBox
     * @throws IOException if FXML file is not found
     */
    public void okButtonAction() throws IOException {
        String selection = campaignsBox.getValue();
        System.out.println(selection);

        for (VaccineCampaign vc: VaccineCampaignService.getCampaigns()) {
            if (vc.getName().equals(selection))
                CampaignToEdit.campaign = vc;
        }

        App.setRoot("editCampaign");
    }

    /**
     * Goes back to the welcome screen
     * @throws IOException if FXML file is not found
     */
    public void logoutAction() throws IOException {
        clearComboBox();
        App.setRoot("welcome");
    }

    /**
     * Goes to the view to create a new vaccine campaign
     * @throws IOException if FXML file is not found
     */
    public void newVaccineCampaign() throws IOException {
        CampaignToAdd.campaign = new CampaignToAddFields();
        App.setRoot("addCampaign");
    }

    /**
     * Clears the selection and content of the ComboBox objects on this view
     */
    public void clearComboBox() {
        campaignsBox.getSelectionModel().clearSelection();
        campaignsBox.getItems().clear();
    }
}
