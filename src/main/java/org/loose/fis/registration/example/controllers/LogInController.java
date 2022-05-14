package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.registration.example.Main;
import org.loose.fis.registration.example.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.registration.example.services.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class LogInController{

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;





    @FXML
    public void handleLogInAction() {
        try{
            UserService.checkUserDoesNotAlreadyExist(usernameField.getText());
            registrationMessage.setText("Wrong username or password");
        }catch(UsernameAlreadyExistsException e){
            if(UserService.checkPasswordForUser(usernameField.getText(),passwordField.getText()))
                registrationMessage.setText("corect username");
            else
                registrationMessage.setText("Wrong username or password");
        }
    }

    @FXML
    public void handleRegisterAction(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();}
    }
}
