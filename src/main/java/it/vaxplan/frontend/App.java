package it.vaxplan.frontend;

import it.vaxplan.backend.json.Sync;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("welcome"));
        stage.setTitle("Vaxplan");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.setOnCloseRequest(e -> {
            e.consume();
            try {
                closeProgram(stage);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        stage.show();
    }

    public static void newWindow(String fxml) throws IOException {
        var stage = new Stage();
        stage.setScene(new Scene(loadFXML(fxml)));
        stage.setResizable(false);
        stage.show();
    }

    private void closeProgram(Stage stage) throws IOException {
        // Sync Vaccine campaigns
        Sync.writeVaccineCampaignServiceToJson();

        // Sync patient database (registered users)
        Sync.writePatientServiceToJson();

        System.out.println("Closing");

        stage.close();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        var fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
