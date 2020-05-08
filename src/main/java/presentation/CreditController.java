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
import java.util.ArrayList;
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
        if (App.getCreditManager().getCreditProductionID() != 0) {
            tfProductionID.setText(String.valueOf(App.getCreditManager().getCreditProductionID()));
        } else {
            tfProductionID.setText("No production selected");
        }
        lbCurrentUser.setText("Logget på som: " + App.getAuthentificationManager().getCurrentUser().getUsername());
    }

    public void createSingleCredit(ActionEvent actionEvent) {
        if (tfCreditRole.getText().isEmpty() | tfFirstName.getText().isEmpty() | tfFirstName.getText().isEmpty()) {
            notificationAnimationSetter(spNotificationBox, spNotificationText, "spNotificationBox-deleted",
                    Credit.class.getSimpleName(), 0, btnCreate, btnDelete, btnUpdate);
            return;
        }

        App.getCreditManager().createCredit(Integer.parseInt(tfProductionID.getText()), tfCreditRole.getText(), tfFirstName.getText(), tfLastName.getText());
        tvCreditTable.setItems(FXCollections.observableArrayList(App.getCreditManager().getCreditList()));
        tfCreditRole.clear();
        tfFirstName.clear();
        tfLastName.clear();

        notificationAnimationSetter(spNotificationBox, spNotificationText, "spNotificationBox-created",
                Credit.class.getSimpleName(), 1, btnCreate, btnDelete, btnUpdate);
    }

    public void updateCredit(ActionEvent actionEvent) {
        App.getCreditManager().updateCredit(tvCreditTable.getSelectionModel().getSelectedItem(), tfCreditRole.getText(), tfFirstName.getText(), tfLastName.getText());
        tvCreditTable.refresh();

        notificationAnimationSetter(spNotificationBox, spNotificationText, "spNotificationBox-updated",
                Credit.class.getSimpleName(), 2, btnCreate, btnDelete, btnUpdate);
    }

    public void deleteCredit(ActionEvent actionEvent) {
        App.getCreditManager().deleteCredit(tvCreditTable.getSelectionModel().getSelectedItem(), Integer.parseInt(tfProductionID.getText()));
        tvCreditTable.setItems(FXCollections.observableArrayList(App.getCreditManager().getCreditList()));

        notificationAnimationSetter(spNotificationBox, spNotificationText, "spNotificationBox-deleted",
                Credit.class.getSimpleName(), 3, btnCreate, btnDelete, btnUpdate);
    }

    public void searchFunctionality(ActionEvent actionEvent) {
        ArrayList<Credit> searchResult = new ArrayList<>();
        String searchText = tfSearch.getText().toLowerCase();
        for (int i = 0; i < App.getCreditManager().getCreditList().size(); i++) {
            if (App.getCreditManager().getCreditList().get(i).toString().toLowerCase().contains(searchText)) {
                searchResult.add(App.getCreditManager().getCreditList().get(i));
            }
        }
        tvCreditTable.setItems((FXCollections.observableArrayList(searchResult)));
    }

    public void selectCredit(MouseEvent mouseEvent) {
        tfCreditRole.setText(tvCreditTable.getSelectionModel().getSelectedItem().getRole());
        tfFirstName.setText(tvCreditTable.getSelectionModel().getSelectedItem().getFirstName());
        tfLastName.setText(tvCreditTable.getSelectionModel().getSelectedItem().getLastName());
    }
}
