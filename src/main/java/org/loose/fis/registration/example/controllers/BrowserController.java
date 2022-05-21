package org.loose.fis.registration.example.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.registration.example.model.User;
import org.loose.fis.registration.example.services.UserService;
import javafx.collections.FXCollections;

import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class BrowserController extends AdminMenuController{


    @FXML
    private Text usernameMessage;
    @FXML
    private Text nameMessage;
    @FXML
    private Text phoneMessage;
    @FXML
    private Text hoursMessage;
    @FXML
    private Text salaryMessage;
    @FXML
    private ListView listField;


    @FXML
    public void initialize()
    {

        ObservableList<String> list= FXCollections.observableArrayList(UserService.getFullName("Client",selectedUser.getCode()));
        listField.setItems(list);

        listField.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                User client=UserService.checkUserFullName(listField.getSelectionModel().getSelectedItem().toString());
                usernameMessage.setText(client.getUsername());
                nameMessage.setText(client.getFull_name());
                phoneMessage.setText(client.getPhone());
                hoursMessage.setText(String.valueOf(client.getHours()));
                salaryMessage.setText(String.valueOf(client.getSalary()));
            }
        });

    }
    @FXML
    public void handleGoBackAction(javafx.event.ActionEvent actionEvent){
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menu_admin.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
