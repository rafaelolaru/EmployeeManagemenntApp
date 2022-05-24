package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientMenuController {
    @FXML
    public void makeComplaint(javafx.event.ActionEvent actionEvent){
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("complaints.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }


    @FXML
    public void handleExitAction(){
        System.exit(1);
    }
}
