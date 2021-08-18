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

    /**
     * Adds a category of patients selected from the list visible on the left to the list
     * visible on the right
     */
    public void addCategory() {
        var toAdd = availableCategoriesList.getSelectionModel().getSelectedItem();
        selectedCategoriesList.getItems().add(toAdd);
        availableCategoriesList.getItems().remove(toAdd);
        selectedCategoriesList.refresh();
        availableCategoriesList.refresh();
    }

    /**
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
    public void confirmButtonAction() {
        Set<PatientCategories> setToAdd = new TreeSet<>(selectedCategoriesList.getItems());
        CampaignToAdd.campaign.setPatientCategories(setToAdd);
    }

}
