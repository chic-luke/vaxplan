package it.vaxplan.frontend;

import it.vaxplan.backend.Vaccine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.ResourceBundle;

import it.vaxplan.backend.VaccineCampaign;
import it.vaxplan.backend.VaccineCampaignService;

public class AdminScreenController implements Initializable {

    @FXML
    public ComboBox<String> campaignList;
    @FXML
    public Button cmpsOK;
    @FXML
    public Button logoutButton;

    private LinkedList<String> tmpList = new LinkedList<>();
    private static boolean flag = false;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var vaccineCampaigns = VaccineCampaignService.getCampaigns();

        // Begin placeholder code
        if (!flag) {
            VaccineCampaign camp1 = VaccineCampaign.builder()
                    .name("COVID-19")
                    .vaccine(Vaccine.COVID)
                    .availableDoses(1000)
                    .dailyStartTime(LocalTime.of(9, 0))
                    .dailyEndTime(LocalTime.of(20, 0))
                    .build();

            VaccineCampaign camp2 = VaccineCampaign.builder()
                    .name("Chickenpox")
                    .vaccine(Vaccine.CHICKENPOX)
                    .availableDoses(1000)
                    .dailyStartTime(LocalTime.of(9, 0))
                    .dailyEndTime(LocalTime.of(20, 0))
                    .build();

            VaccineCampaignService.addCampaign(camp1);
            VaccineCampaignService.addCampaign(camp2);
            flag = true;
        }
        // End placeholder code

        tmpList.clear();
        for (VaccineCampaign c: vaccineCampaigns) {
            System.out.println("Name: " + c.getName());
            tmpList.add(c.getName());
        }

        System.out.println(tmpList);

        campaignList.setPromptText("Seleziona campagna vaccinale");
        clearComboBox();
        campaignList.getItems().addAll(tmpList);
    }

    public void okButtonAction() {
        var selection = campaignList.getValue();
        System.out.println(selection);
    }

    public void logoutAction() throws IOException {
        clearComboBox();
        LoginScreen.setRoot("welcome");
    }

    public void clearComboBox() {
        campaignList.getSelectionModel().clearSelection();
        campaignList.getItems().clear();
    }
}
