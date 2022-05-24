package org.loose.fis.registration.example.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.loose.fis.registration.example.model.User;
import org.loose.fis.registration.example.services.UserService;


public class AddSolutionController extends LogInController {
    @FXML
    TextArea textSolution;
    public void onSubmit(javafx.event.ActionEvent actionEvent){

        User user1 = UserService.checkAdmin(selectedUser.getCode());
        user1.complaints.setMessage(textSolution.getText());
        UserService.setCode(user1);
        System.out.println(textSolution.getText());

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

    public void handleGoBackAction(javafx.event.ActionEvent actionEvent){
        try {

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menu_admin.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.getMessage();

        }
    }

}
