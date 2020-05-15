package presentation;

import domain.Credit;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CreditController extends MainController implements Initializable {
    public TextField tfCreditRole;
    public TextField tfFirstName;
    public TextField tfLastName;
    public TableView<Credit> tvCreditTable;
    public TableColumn<Credit, String> tcId;
    public TableColumn<Credit, String> tcRole;
    public TableColumn<Credit, String> tcFirstName;
    public TableColumn<Credit, String> tcLastName;
    public TextField tfProductionID;
    public Label lbCurrentUser;
    public StackPane spNotificationBox;
    public Text spNotificationText;
    public Button btnCreate;
    public Button btnUpdate;
    public Button btnDelete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vbSideBarLogo.setImage(new Image(new File("logo-ajate.png").toURI().toString()));
        tcId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tcRole.setCellValueFactory(new PropertyValueFactory<>("Role"));
        tcFirstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        tcLastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        tvCreditTable.setItems(FXCollections.observableArrayList(App.getCreditManager().getCreditList()));
        tfProductionID.setDisable(true);
        tfProductionID.setText(String.valueOf(App.getCreditManager().getCreditProductionID()));
        lbCurrentUser.setText("Logget på som: " + App.getAuthentificationManager().getCurrentUser().getUsername());
    }

    public void createCredit(ActionEvent actionEvent) {
        if (tfCreditRole.getText().isEmpty() | tfFirstName.getText().isEmpty() | tfFirstName.getText().isEmpty()) {
            notificationAnimationSetter(spNotificationBox, spNotificationText, "Krediteringen", 0);
            return;
        }
        try {
            App.getCreditManager().createCredit(Integer.parseInt(tfProductionID.getText()), tfCreditRole.getText(), tfFirstName.getText(), tfLastName.getText());
            notificationAnimationSetter(spNotificationBox, spNotificationText, "Krediteringen", 1);
            tvCreditTable.setItems(FXCollections.observableArrayList(App.getCreditManager().getCreditList()));
            tfCreditRole.clear();
            tfFirstName.clear();
            tfLastName.clear();
        } catch (NullPointerException ex) {
            notificationAnimationSetter(spNotificationBox, spNotificationText, "Krediteringen", 5);
        } catch (SQLException ex) {
            notificationAnimationSetter(spNotificationBox, spNotificationText, "Krediteringen", 9);
        }
    }

    public void updateCredit(ActionEvent actionEvent) {
        if (tfCreditRole.getText().isEmpty() | tfFirstName.getText().isEmpty() | tfFirstName.getText().isEmpty()) {
            notificationAnimationSetter(spNotificationBox, spNotificationText, "Krediteringen", 0);
            return;
        }
        App.getCreditManager().updateCredit(tvCreditTable.getSelectionModel().getSelectedItem(), tfCreditRole.getText(), tfFirstName.getText(), tfLastName.getText());
        tvCreditTable.refresh();

        notificationAnimationSetter(spNotificationBox, spNotificationText, "Krediteringen", 2);
    }

    public void deleteCredit(ActionEvent actionEvent) {
        if (tfCreditRole.getText().isEmpty() | tfFirstName.getText().isEmpty() | tfFirstName.getText().isEmpty()) {
            notificationAnimationSetter(spNotificationBox, spNotificationText, "Krediteringen", 0);
            return;
        }
        App.getCreditManager().deleteCredit(tvCreditTable.getSelectionModel().getSelectedItem(), Integer.parseInt(tfProductionID.getText()));
        tvCreditTable.setItems(FXCollections.observableArrayList(App.getCreditManager().getCreditList()));

        notificationAnimationSetter(spNotificationBox, spNotificationText, "Krediteringen", 3);

        tfCreditRole.clear();
        tfFirstName.clear();
        tfLastName.clear();
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    @Override
    public void searchFunctionality(ActionEvent actionEvent) {
        List<Credit> searchResult = App.getCreditManager().readCredit(tfSearch.getText());
        tvCreditTable.setItems((FXCollections.observableArrayList(searchResult)));
    }

    public void selectCredit(MouseEvent mouseEvent) {
        try {
            tfCreditRole.setText(tvCreditTable.getSelectionModel().getSelectedItem().getRole());
            tfFirstName.setText(tvCreditTable.getSelectionModel().getSelectedItem().getFirstName());
            tfLastName.setText(tvCreditTable.getSelectionModel().getSelectedItem().getLastName());
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }catch (NullPointerException ex){}
    }
}
