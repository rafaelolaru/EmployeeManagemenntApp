package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminMenuController extends LogInController{

    @FXML
    public void handleBrowseAction(javafx.event.ActionEvent actionEvent){
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("browse.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch (Exception e1){
            e1.printStackTrace();}
    }

    @FXML
    public void handleAddTransactionAction(javafx.event.ActionEvent actionEvent){
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("add_transaction.fxml"));
            Scene scene = new Scene(root,400,500);
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch (Exception e1){
            e1.printStackTrace();}
    }

    @FXML
    public void handleGetPaymentsAction(javafx.event.ActionEvent actionEvent){
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("get_payments.fxml"));
            Scene scene = new Scene(root,400,600);
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch (Exception e1){
            e1.printStackTrace();}
    }

    @FXML
    public void handleExitAction(){
        System.exit(1);
    }

}
