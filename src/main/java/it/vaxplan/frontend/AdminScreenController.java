package it.vaxplan.frontend;

import it.vaxplan.backend.Vaccine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    public void logoutAction(ActionEvent actionEvent) throws IOException {
        LoginScreen.setRoot("welcome");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var vaccineCampaigns = VaccineCampaignService.getCampaigns();
        LinkedList<String> campaignsList = new LinkedList<>();

        // Begin placeholder code
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

        // End placeholder code

        for (VaccineCampaign c: vaccineCampaigns) {
            System.out.println("Name: " + c.getName());
            campaignsList.add(c.getName());
        }

        System.out.println(campaignsList);

        campaignList.getItems().addAll(campaignsList);
    }
}
