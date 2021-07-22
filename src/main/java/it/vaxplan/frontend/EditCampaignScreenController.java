package it.vaxplan.frontend;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditCampaignScreenController implements Initializable {

    @FXML
    public TextField dosesAddField;
    @FXML
    public Button dosesAddOKButton;
    @FXML
    public TextField dosesSetTextField;
    @FXML
    public Button dosesCtlOKButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Editing:" + Globals.campaignToEdit);
    }

    public void backButtonAction() throws IOException {
        LoginScreen.setRoot("adminscreen");
    }

    public void addDoses() {
        Globals.campaignToEdit.addDoses(Integer.parseInt(dosesAddField.getText()));
    }
}
