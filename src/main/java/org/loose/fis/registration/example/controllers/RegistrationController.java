package org.loose.fis.registration.example.controllers;

import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.registration.example.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.registration.example.services.UserService;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.function.UnaryOperator;

public class RegistrationController {

    @FXML
    public Text registrationMessage;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;
    @FXML
    public ChoiceBox role;
    @FXML
    public TextField fullNameField;
    @FXML
    public TextField phoneField;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Admin");
    }


    @FXML
    public void handleRegisterAction() {

        if(usernameField.getText().matches("  ")||usernameField.getText().equals(""))
        {

        }
        else
            if(passwordField.getText().length()<5)
            {

            }
            else if(fullNameField.getText().equals(""))
            {

            }
            else if((!isNumeric(phoneField.getText()))||phoneField.getText().length()>=15||phoneField.getText().length()<7) {

            }
            else
                try {
                    UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue(), fullNameField.getText(), phoneField.getText());
                    registrationMessage.setText("Account created successfully!");
                } catch (Exception e) {
                    registrationMessage.setText(e.getMessage());}
        }

    @FXML
    public void handleGoBackAction(){
        try {

        } catch (Exception e) {
            registrationMessage.setText(e.getMessage());
        }
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }


}
