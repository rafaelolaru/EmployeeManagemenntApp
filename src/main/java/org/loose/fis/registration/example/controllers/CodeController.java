package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.registration.example.model.User;
import org.loose.fis.registration.example.services.UserService;

public class CodeController extends LogInController{

    @FXML
    public Text roleMessage;
    @FXML
    public TextField codeField;
    @FXML
    public Text codeMessage;


    public void setUserCode(String code){
        selectedUser=new User();
        selectedUser.setCode(code);
    }
    public void setUserRole(String role){selectedUser.setRole(role);}

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
    public void handleInsertAction() {
        if (codeField.getText().length() < 6) {
            codeMessage.setText("Code is Too Small");
        } else {
            if (selectedUser.getRole().equals("Admin")) {
                if ((UserService.checkCode(codeField.getText())))
                    codeMessage.setText("Code already exists");
                else {
                    selectedUser.setCode(codeField.getText());
                    UserService.setCode(selectedUser);
                    codeMessage.setText("Code created");

                }
            } else if (selectedUser.getRole().equals("Client")) {
                if (!(UserService.checkCode(codeField.getText())))
                    codeMessage.setText("Code does not exists");
                else {
                    codeMessage.setText("Code found");
                    selectedUser.setCode(codeField.getText());
                    UserService.setCode(selectedUser);

                }
            }
        }
    }
}
