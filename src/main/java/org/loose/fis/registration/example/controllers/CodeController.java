package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.registration.example.services.UserService;

public class CodeController extends LogInController{

    @FXML
    private Text roleMessage;
    @FXML
    private TextField codeField;
    @FXML
    private Text codeMessage;


    @FXML
    private void initialize(){
        if(selectedUser.getRole().equals("Admin"))
            roleMessage.setText("Please create a new code");
        else
        if(selectedUser.getRole().equals("Client")) {
            roleMessage.setText("Please insert the code from employer");
        }
    }

    @FXML
    private void handleInsertAction(javafx.event.ActionEvent actionEvent) {
        if (codeField.getText().length() < 6) {
            codeMessage.setText("Code is Too Small");
        } else {
            if (selectedUser.getRole().equals("Admin")) {
                if ((UserService.checkCode(codeField.getText())))
                    codeMessage.setText("Code already exists");
                else {
                    selectedUser.setCode(codeField.getText());
                    UserService.setCode(selectedUser);
                    try {
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menu_admin.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                }
            } else if (selectedUser.getRole().equals("Client")) {
                if (!(UserService.checkCode(codeField.getText())))
                    codeMessage.setText("Code does not exists");
                else {
                    selectedUser.setCode(codeField.getText());
                    UserService.setCode(selectedUser);
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
            }
        }
    }
}
