package presentation;

import domain.Credit;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreditController extends MainController implements Initializable {
    public TextField tfCreditRole;
    public TextField tfCreditName;
    public TextArea taCreditsArea;
    public TableView<Credit> tvCreditTable;
    public TableColumn<Credit, String> tcId;
    public TableColumn<Credit, String> tcRole;
    public TableColumn<Credit, String> tcFirstName;
    public TableColumn<Credit, String> tcLastName;
    public TextField tfProductionID;
    public Label lbCurrentUser;
    public TextField tfFirstName;
    public TextField tfLastName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vbSideBarLogo.setImage(new Image(new File("logo-ajate.png").toURI().toString()));

        tcId.setCellValueFactory(new PropertyValueFactory<>("CreditId"));
        tcRole.setCellValueFactory(new PropertyValueFactory<>("CreditRole"));
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

    // Create single credit handler
    public void createSingleCredit(ActionEvent actionEvent) {
        App.getCreditManager().createCredit(tfCreditRole.getText(), tfFirstName.getText(), tfLastName.getText(), Integer.parseInt(tfProductionID.getText()));
        tvCreditTable.setItems(FXCollections.observableArrayList(App.getCreditManager().getCreditList()));
        App.getCreditManager().saveCredits(tfProductionID.getText());
    }

    // Update credit handler
    public void updateCredit(ActionEvent actionEvent) {
        App.getCreditManager().updateCredit(tvCreditTable.getSelectionModel().getSelectedItem(), tfCreditRole.getText(), tfFirstName.getText(), tfLastName.getText());
        App.getCreditManager().saveCredits(tfProductionID.getText());
        tvCreditTable.refresh();
    }

    // Delete credit handler
    public void deleteCredit(ActionEvent actionEvent) {
        App.getCreditManager().deleteCredit(tvCreditTable.getSelectionModel().getSelectedItem());
        App.getCreditManager().saveCredits(tfProductionID.getText());
        tvCreditTable.setItems(FXCollections.observableArrayList(App.getCreditManager().getCreditList()));
    }

    // Search functionality handler
    public void searchFunctionality(ActionEvent actionEvent) {
        ArrayList<Credit> searchResult = new ArrayList<>();
        String searchText = tfSearch.getText().toLowerCase();
        for (int i = 0; i < App.getCreditManager().getCreditList().size(); i++){
            if (App.getCreditManager().getCreditList().get(i).toString().toLowerCase().contains(searchText)){
                searchResult.add(App.getCreditManager().getCreditList().get(i));
            }
        }
        tvCreditTable.setItems((FXCollections.observableArrayList(searchResult)));
    }

    // Select credit handler
    public void selectCredit(MouseEvent mouseEvent) {
        tfCreditRole.setText(tvCreditTable.getSelectionModel().getSelectedItem().getRole());
        tfFirstName.setText(tvCreditTable.getSelectionModel().getSelectedItem().getFirstName());
        tfLastName.setText(tvCreditTable.getSelectionModel().getSelectedItem().getLastName());
    }
}
