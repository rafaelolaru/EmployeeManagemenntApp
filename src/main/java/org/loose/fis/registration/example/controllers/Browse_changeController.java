package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.registration.example.model.User;
import org.loose.fis.registration.example.services.UserService;

public class Browse_changeController extends BrowserController{

    @FXML
    private Text typeMessage;
    @FXML
    private TextField codeField;
    @FXML
    private Text codeMessage;


    @FXML
    public void initialize() {
        if (this.type != null) {
            if (type.equals("Salary"))
                typeMessage.setText("Please change Salary");
            else if (type.equals("Hours")) {
                typeMessage.setText("Please add more hours");
            }
        }
    }

    @FXML
    private void handleInsertAction(javafx.event.ActionEvent actionEvent) {
        if (isNumeric(codeField.getText())) {
            if (type.equals("Salary")) {
                client.setSalary(Integer.valueOf(codeField.getText()));
            } else if (type.equals("Hours")) {
                client.addHours(Integer.valueOf(codeField.getText()));
            }
            UserService.setUserValues(client);

            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("browse.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (Exception b) {
                b.printStackTrace();
            }
        }
        else
            codeMessage.setText("Please only insert numbers");

    }
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
