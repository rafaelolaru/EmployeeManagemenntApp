package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.registration.example.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.registration.example.model.User;
import org.loose.fis.registration.example.services.UserService;

public class LogInController{

    @FXML
    public Text registrationMessage;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;

    public static User selectedUser;




    @FXML
    public void handleLogInAction() {
        try{
            UserService.checkUserDoesNotAlreadyExist(usernameField.getText());
            registrationMessage.setText("Wrong username or password");
        }catch(UsernameAlreadyExistsException e){
            if(UserService.checkPasswordForUser(usernameField.getText(),passwordField.getText())) {
                selectedUser=UserService.getUsername(usernameField.getText());
                registrationMessage.setText("Account found");
                if(selectedUser.getCode()!=null)
                {}

            }
            else
                registrationMessage.setText("Wrong username or password");

        }
    }

}
