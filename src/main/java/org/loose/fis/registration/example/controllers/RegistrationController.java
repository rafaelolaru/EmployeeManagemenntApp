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
import org.loose.fis.registration.example.exceptions.UsernameAlreadyExistsException;
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
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue(),fullNameField.getText(),phoneField.getText());
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
}
