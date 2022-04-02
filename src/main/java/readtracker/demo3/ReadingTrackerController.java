package readtracker.demo3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ReadingTrackerController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}