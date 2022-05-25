package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ProfileController extends LogInController{
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
    private void initialize(){
        usernameMessage.setText(selectedUser.getUsername());
        nameMessage.setText(selectedUser.getFull_name());
        phoneMessage.setText(selectedUser.getPhone());
        hoursMessage.setText(String.valueOf(selectedUser.getHours()));
        salaryMessage.setText(String.valueOf(selectedUser.getSalary()));
    }
    @FXML
    public void handleGoBackAction(javafx.event.ActionEvent actionEvent){
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menu_client.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception b1) {
            b1.printStackTrace();
        }
    }

}