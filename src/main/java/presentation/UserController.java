package presentation;

import domain.Producer;
import domain.Systemadministrator;
import domain.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
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
    public TableColumn<Systemadministrator, String> tcAdminEmail;
    public TableColumn<Systemadministrator, String> tcAdminFirstName;
    public TableColumn<Systemadministrator, String> tcAdminLastName;
    public TableColumn<Producer, String> tcProducerID;
    public TableColumn<Producer, String> tcProducerUsername;
    public TableColumn<Producer, String> tcProducerEmail;
    public TableColumn<Producer, String> tcProducerFirstName;
    public TableColumn<Producer, String> tcProducerLastName;
    public TableColumn<Producer, String> tcProducerEmployedBy;
    public TableView<Systemadministrator> tvAdmin;
    public TableView<Producer> tvProducer;
    public Label lbCurrentUser;
    public StackPane spNotificationBox;
    public Text spNotificationText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vbSideBarLogo.setImage(new Image(new File("logo-ajate.png").toURI().toString()));

        tcAdminID.setCellValueFactory(new PropertyValueFactory<>("AdminId"));
        tcAdminUsername.setCellValueFactory(new PropertyValueFactory<>("Username"));
        tcAdminEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        tcAdminFirstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        tcAdminLastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));

        tcProducerID.setCellValueFactory(new PropertyValueFactory<>("ProducerId"));
        tcProducerUsername.setCellValueFactory(new PropertyValueFactory<>("Username"));
        tcProducerEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        tcProducerFirstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        tcProducerLastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        tcProducerEmployedBy.setCellValueFactory(new PropertyValueFactory<>("EmployedBy"));

        tvAdmin.setItems(FXCollections.observableArrayList(App.getUserManager().getAdminList()));
        tvProducer.setItems(FXCollections.observableArrayList(App.getUserManager().getProducerList()));

        lbCurrentUser.setText("Logget på som: " + App.getAuthentificationManager().getCurrentUser().getUsername());
    }

    public void createUser(ActionEvent actionEvent) {
        if (!App.getAuthentificationManager().checkPermission()) {
            notificationAnimationSetter(spNotificationBox, spNotificationText, User.class.getSimpleName(), 4);
            return;
        }
        try {
            App.getUserManager().createUser(tfUsername.getText(), tfPassword.getText(), tfEmail.getText(), tfFirstName.getText(), tfLastName.getText(), Integer.parseInt(tfAccessLevel.getText()), tfEmployedBy.getText());
            tvAdmin.setItems(FXCollections.observableArrayList(App.getUserManager().getAdminList()));
            tvProducer.setItems(FXCollections.observableArrayList(App.getUserManager().getProducerList()));
        }catch (NumberFormatException e){
            notificationAnimationSetter(spNotificationBox, spNotificationText, User.class.getSimpleName(), 0);
            return;
        }
        notificationAnimationSetter(spNotificationBox, spNotificationText, User.class.getSimpleName(), 1);
    }

    public void updateUser(ActionEvent actionEvent) {
        if (!App.getAuthentificationManager().checkPermission()) {
            notificationAnimationSetter(spNotificationBox, spNotificationText, User.class.getSimpleName(), 4);
            return;
        }
        try {
            if (Integer.parseInt(tfAccessLevel.getText()) == 1) {
                App.getUserManager().updateAdmin(tvAdmin.getSelectionModel().getSelectedItem(),
                        tfUsername.getText(), tfPassword.getText(), tfEmail.getText(),
                        tfFirstName.getText(), tfLastName.getText(), Integer.parseInt(tfAccessLevel.getText()), tfEmployedBy.getText());
                tvAdmin.refresh();
            } else {
                App.getUserManager().updateProducer(tvProducer.getSelectionModel().getSelectedItem(),
                        tfUsername.getText(), tfPassword.getText(), tfEmail.getText(),
                        tfFirstName.getText(), tfLastName.getText(), Integer.parseInt(tfAccessLevel.getText()), tfEmployedBy.getText());
                tvProducer.refresh();
            }


        }catch (NumberFormatException e){
            notificationAnimationSetter(spNotificationBox, spNotificationText, User.class.getSimpleName(), 6);
            return;
        }
        notificationAnimationSetter(spNotificationBox, spNotificationText, User.class.getSimpleName(), 2);
    }

    public void searchFunctionality(ActionEvent actionEvent) {
        String searchText = tfSearch.getText().toLowerCase();
        tvAdmin.setItems(FXCollections.observableArrayList(App.getUserManager().readAdmin(searchText)));
        tvProducer.setItems((FXCollections.observableArrayList(App.getUserManager().readProducer(searchText))));
    }

    public void deleteAdmin(ActionEvent actionEvent) {
        if (!App.getAuthentificationManager().checkPermission()) {
            notificationAnimationSetter(spNotificationBox, spNotificationText, User.class.getSimpleName(), 4);
            return;
        }
        try {
            App.getUserManager().deleteAdmin(tvAdmin.getSelectionModel().getSelectedItem());
            tvAdmin.setItems(FXCollections.observableArrayList(App.getUserManager().getAdminList()));
        }catch (NullPointerException e){
            notificationAnimationSetter(spNotificationBox, spNotificationText, User.class.getSimpleName(), 5);
            return;
        }

        notificationAnimationSetter(spNotificationBox, spNotificationText, User.class.getSimpleName(), 3);
    }

    public void deleteProducer(ActionEvent actionEvent) {
        if (!App.getAuthentificationManager().checkPermission()) {
            notificationAnimationSetter(spNotificationBox, spNotificationText, User.class.getSimpleName(), 4);
            return;
        }
        try {
            App.getUserManager().deleteProducer(tvProducer.getSelectionModel().getSelectedItem());
            tvProducer.setItems(FXCollections.observableArrayList(App.getUserManager().getProducerList()));
        }catch (NullPointerException e){
            notificationAnimationSetter(spNotificationBox, spNotificationText, User.class.getSimpleName(), 5);
            return;
        }
        notificationAnimationSetter(spNotificationBox, spNotificationText, User.class.getSimpleName(), 3);
    }

    public void selectProducer(MouseEvent mouseEvent) {
        tfEmployedBy.clear();
        try {
            tfUsername.setText(tvProducer.getSelectionModel().getSelectedItem().getUsername());
            tfPassword.setText(tvProducer.getSelectionModel().getSelectedItem().getPassword());
            tfEmail.setText(tvProducer.getSelectionModel().getSelectedItem().getEmail());
            tfFirstName.setText(tvProducer.getSelectionModel().getSelectedItem().getFirstName());
            tfLastName.setText(tvProducer.getSelectionModel().getSelectedItem().getLastName());
            tfAccessLevel.setText(String.valueOf(tvProducer.getSelectionModel().getSelectedItem().getAccessLevel()));
            tfEmployedBy.setText(tvProducer.getSelectionModel().getSelectedItem().getEmployedBy());
        }catch (NullPointerException e){ }
    }

    public void selectAdmin(MouseEvent mouseEvent) {
        tfEmployedBy.clear();
        try {
            tfUsername.setText(tvAdmin.getSelectionModel().getSelectedItem().getUsername());
            tfPassword.setText(tvAdmin.getSelectionModel().getSelectedItem().getPassword());
            tfEmail.setText(tvAdmin.getSelectionModel().getSelectedItem().getEmail());
            tfFirstName.setText(tvAdmin.getSelectionModel().getSelectedItem().getFirstName());
            tfLastName.setText(tvAdmin.getSelectionModel().getSelectedItem().getLastName());
            tfAccessLevel.setText(String.valueOf(tvAdmin.getSelectionModel().getSelectedItem().getAccessLevel()));
        }catch (NullPointerException e){ }
    }
}
