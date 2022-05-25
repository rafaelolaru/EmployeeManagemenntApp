package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.loose.fis.registration.example.complaints.Complaints;
import org.loose.fis.registration.example.services.UserService;


public class ComplaintsController extends LogInController {
    @FXML
    TextArea textComplaints;
    public void onSubmit(javafx.event.ActionEvent actionEvent){

        UserService.setComplaints(selectedUser,new Complaints(textComplaints.getText()));

        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menu_client.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void handleGoBackAction(javafx.event.ActionEvent actionEvent){
        try {

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menu_client.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.getMessage();

        }
    }

}
