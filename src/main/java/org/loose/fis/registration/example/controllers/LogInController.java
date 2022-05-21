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
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;

    public static User selectedUser;




    @FXML
    public void handleLogInAction(javafx.event.ActionEvent actionEvent) {
        try{
            UserService.checkUserDoesNotAlreadyExist(usernameField.getText());
            registrationMessage.setText("Wrong username or password");
        }catch(UsernameAlreadyExistsException e){
            if(UserService.checkPasswordForUser(usernameField.getText(),passwordField.getText())) {
                selectedUser=UserService.getUsername(usernameField.getText());
                if(selectedUser.getCode()!=null)
                {
                    if(selectedUser.getRole().equals("Admin"))
                    try {
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menu_admin.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                    else  if(selectedUser.getRole().equals("Client")){
                        try {
                            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menu_client.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch (Exception exc2) {
                            exc2.printStackTrace();
                        }
                    }
                }
                else{
                    try {
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("user_code.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                }

            }
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
