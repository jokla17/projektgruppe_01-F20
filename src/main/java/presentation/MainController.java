package presentation;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;

public class MainController {
    public StackPane navCreateUser;
    public StackPane navCreateCredits;
    public StackPane navCreateProduction;
    public StackPane navHome;
    public VBox vbSideBarCenter;
    public Button btnSearch;
    public TextField tfSearch;
    public VBox vbSideBarHeader;
    public VBox vbSideBarFooter;
    public HBox hbTopHeaderBar;
    public ImageView vbSideBarLogo;

    public void searchFunctionality(ActionEvent actionEvent) {}

    public void navigateToHome(MouseEvent mouseEvent) throws IOException {
        App.setRoot("home");
    }

    public void navigateToCreateProduction(MouseEvent mouseEvent) throws IOException {
        App.setRoot("production");
    }

    public void navigateToCreateCredits(MouseEvent mouseEvent) throws IOException {
        App.setRoot("credit");
    }

    public void navigateToCreateUsers(MouseEvent mouseEvent) throws IOException {
        App.setRoot("user");
    }

    public void Logout(ActionEvent actionEvent) throws IOException {
        App.setRoot("Login");
    }

    public void notificationAnimationSetter(StackPane stackPane, Text text, String objectType, int type, Button... buttons) {
        switch (type) {
            case 1: stackPane.setId("spNotificationBox-created"); break;
            case 2: stackPane.setId("spNotificationBox-updated"); break;
            case 3: case 4: case 0: stackPane.setId("spNotificationBox-deleted"); break;
        }

        text.setText(App.getNotificationManager().notificationSwitch(objectType, type));
        stackPane.setVisible(true);

        for (Button b : buttons) {
            b.setDisable(true);
        }

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(600));
        translateTransition.setNode(stackPane);
        translateTransition.setFromY(200);
        translateTransition.setToY(0);
        translateTransition.play();

        PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
        pauseTransition.play();

        pauseTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                translateTransition.setDuration(Duration.millis(600));
                translateTransition.setNode(stackPane);
                translateTransition.setFromY(0);
                translateTransition.setToY(200);
                translateTransition.play();

                for (Button b : buttons) {
                    b.setDisable(false);
                }

                translateTransition.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stackPane.setVisible(false);
                    }
                });
            }
        });
    }
}
