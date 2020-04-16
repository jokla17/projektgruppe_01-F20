package presentation;

import domain.Producer;
import domain.Systemadministrator;
import domain.User;
import domain.UserManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController extends MainController implements Initializable {
    public Button btnCreate;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnLogout;
    public TextField tfUsername;
    public TextField tfPassword;
    public TextField tfEmail;
    public TextField tfFirstName;
    public TextField tfLastName;
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
    public TableView<User> tvAdmin;
    public TableView<User> tvProducer;
    public TextField tfAccessLevel;
    public TextField tfEmployedBy;
    UserManager um;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        um = new UserManager();
        tvAdmin.setItems(FXCollections.observableArrayList(um.getAdminList()));
        tvProducer.setItems(FXCollections.observableArrayList(um.getProducerList()));

    }

    public void createUser(ActionEvent actionEvent) {
        if(Integer.parseInt(tfAccessLevel.getText())==2) {
            um.createUser(tfUsername.getText(), tfPassword.getText(), tfEmail.getText(), tfFirstName.getText(), tfLastName.getText(), Integer.parseInt(tfAccessLevel.getText()));
        }
        else {
            um.createUser(tfUsername.getText(), tfPassword.getText(), tfEmail.getText(), tfFirstName.getText(), tfLastName.getText(), Integer.parseInt(tfAccessLevel.getText()), tfEmployedBy.getText());
        }
        tvAdmin.setItems(FXCollections.observableArrayList(um.getAdminList()));
        tvProducer.setItems(FXCollections.observableArrayList(um.getProducerList()));
    }

    public void updateUser(ActionEvent actionEvent) {


    }

    public void deleteAdmin(ActionEvent actionEvent) {
        um.deleteAdmin(tvAdmin.getSelectionModel().getSelectedItem());
        tvAdmin.setItems(FXCollections.observableArrayList(um.getAdminList()));
    }

    public void deleteProducer(ActionEvent actionEvent) {
        um.deleteProducer(tvProducer.getSelectionModel().getSelectedItem());
        tvProducer.setItems(FXCollections.observableArrayList(um.getProducerList()));
    }
}
