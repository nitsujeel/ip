package sunshine;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Sunshine sunshine;
    private Stage stage;
    private boolean exitScheduled = false;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/kuromi.jpg"));
    private Image sunshineImage = new Image(this.getClass().getResourceAsStream("/images/pochacco.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Sunshine instance */
    public void setSunshine(Sunshine s) {
        sunshine = s;
    }

    /** Injects the primary stage so we can close it later. */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /** Shows Sunshine's first message when the app starts. */
    public void showLoadResult(String loadResultString) {
        dialogContainer.getChildren().add(DialogBox.getSunshineDialog(loadResultString, sunshineImage));
    }

    public void showWelcome() {
        dialogContainer.getChildren().add(DialogBox.getSunshineDialog(sunshine.getWelcomeMessage(), sunshineImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Sunshine's reply and then
     * appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sunshine.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSunshineDialog(response, sunshineImage)
        );
        userInput.clear();
        scheduleExitIfBye(input);
    }

    private void scheduleExitIfBye(String input) {
        if (exitScheduled || input == null) {
            return;
        }
        if ("bye".equalsIgnoreCase(input.trim())) {
            exitScheduled = true;
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> {
                if (stage != null) {
                    stage.close();
                }
                Platform.exit();
            });
            delay.play();
        }
    }
}
