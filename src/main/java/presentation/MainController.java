package presentation;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

    public void searchFunctionality(ActionEvent actionEvent) {

    }

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

    public void Logout(ActionEvent actionEvent) throws IOException{
        App.setRoot("Login");
    }
}
