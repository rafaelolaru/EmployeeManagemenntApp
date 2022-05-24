package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.registration.example.services.UserService;

public class RegistrationController {

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField phoneField;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Admin");
    }


    @FXML
    public void handleRegisterAction(javafx.event.ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.WARNING);
        if(usernameField.getText().matches("  ")||usernameField.getText().equals(""))
        {
            alert.setContentText("Invalid username");
            alert.show();
        }
        else
            if(passwordField.getText().length()<5)
            {
                alert.setContentText("Password too short");
                alert.show();
            }
            else if(fullNameField.getText().equals(""))
            {
                alert.setContentText("Invalid name");
                alert.show();
            }
            else if((!isNumeric(phoneField.getText()))||phoneField.getText().length()>=15||phoneField.getText().length()<7) {
                alert.setContentText("Invalid phone number");
                alert.show();
            }
            else if(role.getSelectionModel().isEmpty())
            {
                alert.setContentText("Please select a role");
                alert.show();
            }
            else
                try {
                    UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue(), fullNameField.getText(), phoneField.getText());
                    registrationMessage.setText("Account created successfully!");
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    registrationMessage.setText(e.getMessage());}
        }

    @FXML
    public void handleGoBackAction(javafx.event.ActionEvent actionEvent){
        try {
            registrationMessage.setText("Account created successfully!");
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            registrationMessage.setText(e.getMessage());
        }
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }


}
