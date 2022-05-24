package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.loose.fis.registration.example.complaints.Complaints;
import org.loose.fis.registration.example.model.User;
import org.loose.fis.registration.example.services.UserService;


public class ComplaintsController extends LogInController {
    @FXML
    TextArea textComplaints;
    public void onSubmit(){

        UserService.setComplaints(selectedUser,new Complaints(textComplaints.getText()));

    }
    public void setUser(User user){
        selectedUser=user;
    }


}
