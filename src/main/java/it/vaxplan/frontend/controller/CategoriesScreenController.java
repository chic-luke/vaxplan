package it.vaxplan.frontend.controller;

import it.vaxplan.backend.PatientCategories;
import it.vaxplan.frontend.CampaignToAdd;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

public class CategoriesScreenController implements Initializable {

    @FXML
    public ListView<PatientCategories> availableCategoriesList;
    @FXML
    public ListView<PatientCategories> selectedCategoriesList;
    @FXML
    public Button addCategoryButton;
    @FXML
    public Button removeCategoryButton;
    @FXML
    public Button confirmButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        availableCategoriesList.getItems().addAll(PatientCategories.values());
    }

    public void addCategory() {
        var toAdd = availableCategoriesList.getSelectionModel().getSelectedItem();
        selectedCategoriesList.getItems().add(toAdd);
        availableCategoriesList.getItems().remove(toAdd);
        selectedCategoriesList.refresh();
        availableCategoriesList.refresh();
    }

    public void removeCategory() {
        var toRemove = selectedCategoriesList.getSelectionModel().getSelectedItem();
        availableCategoriesList.getItems().add(toRemove);
        selectedCategoriesList.getItems().remove(toRemove);
        selectedCategoriesList.refresh();
        availableCategoriesList.refresh();
    }

    public void confirmButtonAction() {
        Set<PatientCategories> setToAdd = new TreeSet<>();
        setToAdd.addAll(selectedCategoriesList.getItems());
        CampaignToAdd.campaign.setPatientCategories(setToAdd);
    }

}
