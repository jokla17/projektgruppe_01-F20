package presentation;

import domain.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserController extends MainController implements Initializable {
    public Button btnCreate;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnLogout;
    public Button btnSearch;
    public TextField tfUsername;
    public TextField tfPassword;
    public TextField tfEmail;
    public TextField tfFirstName;
    public TextField tfLastName;
    public TextField tfAccessLevel;
    public TextField tfEmployedBy;
    public TextField tfSearch;
    public AnchorPane apMainScreen;
    public TableColumn<Systemadministrator, String> tcAdminID;
    public TableColumn<Systemadministrator, String> tcAdminUsername;
    public TableColumn<Systemadministrator, String> tcAdminPassword;
    public TableColumn<Systemadministrator, String> tcAdminFirstName;
    public TableColumn<Systemadministrator, String> tcAdminLastName;
    public TableColumn<Producer, String> tcProducerID;
    public TableColumn<Producer, String> tcProducerUsername;
    public TableColumn<Producer, String> tcProducerPassword;
    public TableColumn<Producer, String> tcProducerFirstName;
    public TableColumn<Producer, String> tcProducerLastName;
    public TableColumn<Producer, String> tcProducerEmployedBy;
    public TableView<User> tvAdmin;
    public TableView<Producer> tvProducer;
    public Label lbCurrentUser;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vbSideBarLogo.setImage(new Image(new File("logo-ajate.png").toURI().toString()));

        tcAdminID.setCellValueFactory(new PropertyValueFactory<>("AdminId"));
        tcAdminUsername.setCellValueFactory(new PropertyValueFactory<>("Username"));
        tcAdminPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
        tcAdminFirstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        tcAdminLastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));

        tcProducerID.setCellValueFactory(new PropertyValueFactory<>("ProducerId"));
        tcProducerUsername.setCellValueFactory(new PropertyValueFactory<>("Username"));
        tcProducerPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
        tcProducerFirstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        tcProducerLastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        tcProducerEmployedBy.setCellValueFactory(new PropertyValueFactory<>("EmployedBy"));

        tvAdmin.setItems(FXCollections.observableArrayList(App.getUserManager().getAdminList()));
        tvProducer.setItems(FXCollections.observableArrayList(App.getUserManager().getProducerList()));

        lbCurrentUser.setText("Logget på som: " + App.getAuthentificationManager().getCurrentUser().getUsername());
    }

    public void createUser(ActionEvent actionEvent) {
        if(Integer.parseInt(tfAccessLevel.getText()) == 2) {
            App.getUserManager().createUser(tfUsername.getText(), tfPassword.getText(), tfEmail.getText(), tfFirstName.getText(), tfLastName.getText(), Integer.parseInt(tfAccessLevel.getText()), tfEmployedBy.getText());
        }
        else {
            App.getUserManager().createUser(tfUsername.getText(), tfPassword.getText(), tfEmail.getText(), tfFirstName.getText(), tfLastName.getText(), Integer.parseInt(tfAccessLevel.getText()), tfEmployedBy.getText());
        }
        tvAdmin.setItems(FXCollections.observableArrayList(App.getUserManager().getAdminList()));
        tvProducer.setItems(FXCollections.observableArrayList(App.getUserManager().getProducerList()));
    }

    public void updateUser(ActionEvent actionEvent) {
        if (Integer.parseInt(tfAccessLevel.getText()) == 2) {
            App.getUserManager().updateUser(tvAdmin.getSelectionModel().getSelectedItem(),
                    tfUsername.getText(), tfPassword.getText(), tfEmail.getText(),
                    tfFirstName.getText(), tfLastName.getText(), Integer.parseInt(tfAccessLevel.getText()), tfEmployedBy.getText());
            tvAdmin.refresh();
        } else {
            App.getUserManager().updateProducer(tvProducer.getSelectionModel().getSelectedItem(),
                    tfUsername.getText(), tfPassword.getText(), tfEmail.getText(),
                    tfFirstName.getText(), tfLastName.getText(), Integer.parseInt(tfAccessLevel.getText()), tfEmployedBy.getText());
            tvProducer.refresh();
        }
    }

    // Search functionality handler
    public void searchFunctionality(ActionEvent actionEvent) {
        ArrayList<Systemadministrator> searchResultAdmin = new ArrayList<>();
        ArrayList<Producer> searchResultProducer = new ArrayList<>();
        String searchText = tfSearch.getText().toLowerCase();
        for (int i = 0; i < App.getUserManager().getAdminList().size(); i++){
            if (App.getUserManager().getAdminList().get(i).toString().toLowerCase().contains(searchText)){
                searchResultAdmin.add(App.getUserManager().getAdminList().get(i));
            }
        }
        tvAdmin.setItems((FXCollections.observableArrayList(searchResultAdmin)));
        for (int i = 0; i < App.getUserManager().getProducerList().size(); i++){
            if (App.getUserManager().getProducerList().get(i).toString().toLowerCase().contains(searchText)){
                searchResultProducer.add(App.getUserManager().getProducerList().get(i));
            }
        }
        tvProducer.setItems((FXCollections.observableArrayList(searchResultProducer)));
    }

    public void deleteAdmin(ActionEvent actionEvent) {
        App.getUserManager().deleteAdmin((Systemadministrator)tvAdmin.getSelectionModel().getSelectedItem());
        tvAdmin.setItems(FXCollections.observableArrayList(App.getUserManager().getAdminList()));
    }

    public void deleteProducer(ActionEvent actionEvent) {
        App.getUserManager().deleteProducer(tvProducer.getSelectionModel().getSelectedItem());
        tvProducer.setItems(FXCollections.observableArrayList(App.getUserManager().getProducerList()));
    }

    public void selectProducer(MouseEvent mouseEvent) {
        tfUsername.setText(tvProducer.getSelectionModel().getSelectedItem().getUsername());
        tfPassword.setText(tvProducer.getSelectionModel().getSelectedItem().getPassword());
        tfEmail.setText(tvProducer.getSelectionModel().getSelectedItem().getEmail());
        tfFirstName.setText(tvProducer.getSelectionModel().getSelectedItem().getFirstName());
        tfLastName.setText(tvProducer.getSelectionModel().getSelectedItem().getLastName());
        tfAccessLevel.setText(String.valueOf(tvProducer.getSelectionModel().getSelectedItem().getAccessLevel()));
//        tfEmployedBy.setText(tvProducer.getSelectionModel().getSelectedItems().getEmployedBy());
    }

    public void selectAdmin(MouseEvent mouseEvent) {
        tfUsername.setText(tvAdmin.getSelectionModel().getSelectedItem().getUsername());
        tfPassword.setText(tvAdmin.getSelectionModel().getSelectedItem().getPassword());
        tfEmail.setText(tvAdmin.getSelectionModel().getSelectedItem().getEmail());
        tfFirstName.setText(tvAdmin.getSelectionModel().getSelectedItem().getFirstName());
        tfLastName.setText(tvAdmin.getSelectionModel().getSelectedItem().getLastName());
        tfAccessLevel.setText(String.valueOf(tvAdmin.getSelectionModel().getSelectedItem().getAccessLevel()));
    }
}
