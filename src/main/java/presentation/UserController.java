package presentation;

import domain.Producer;
import domain.Systemadministrator;
import domain.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.io.File;
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
    public TextField tfAccessLevel;
    public TextField tfEmployedBy;
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

        tvAdmin.setItems(FXCollections.observableArrayList(App.getUserManager().getAdminList()));
        tvProducer.setItems(FXCollections.observableArrayList(App.getUserManager().getProducerList()));
    }

    public void createUser(ActionEvent actionEvent) {
        if(Integer.parseInt(tfAccessLevel.getText()) == 2) {
            App.getUserManager().createUser(tfUsername.getText(), tfPassword.getText(), tfEmail.getText(), tfFirstName.getText(), tfLastName.getText(), Integer.parseInt(tfAccessLevel.getText()));
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
                    tfFirstName.getText(), tfLastName.getText(), Integer.parseInt(tfAccessLevel.getText()));
            tvAdmin.refresh();
        } else {
            App.getUserManager().updateUser(tvProducer.getSelectionModel().getSelectedItem(),
                    tfUsername.getText(), tfPassword.getText(), tfEmail.getText(),
                    tfFirstName.getText(), tfLastName.getText(), Integer.parseInt(tfAccessLevel.getText()));
            tvProducer.refresh();
        }
    }

    public void deleteAdmin(ActionEvent actionEvent) {
        App.getUserManager().deleteAdmin(tvAdmin.getSelectionModel().getSelectedItem());
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
