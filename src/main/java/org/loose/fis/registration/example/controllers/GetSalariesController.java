package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.loose.fis.registration.example.model.Transaction;

import static org.loose.fis.registration.example.services.TransactionService.transactions;

public class GetSalariesController {
    @FXML
    private Label salariesLabel;

    @FXML
    private TextField startMonthTF;

    @FXML
    private TextField startDayTF;

    @FXML
    private TextField endDayTF;

    @FXML
    private TextField endMonthTF;

    public void initialize(){
        salariesLabel.setVisible(false);
    }

    public void handleGetSalaries(){
        float sum = 0;
        int startDay,endDay,startMonth,endMonth;
        startDay = Integer.parseInt(startDayTF.getText());
        endDay = Integer.parseInt(endDayTF.getText());
        startMonth = Integer.parseInt(startMonthTF.getText());
        endMonth = Integer.parseInt(endMonthTF.getText());
        for(Transaction transaction : transactions){
            if(transaction.getDay() >= startDay && transaction.getDay() <= endDay &&
                    transaction.getMonth() >= startMonth && transaction.isSalary()
                    && transaction.getMonth() <= endMonth){
                sum += transaction.getPayment();
            }
        }
        salariesLabel.setText(String.valueOf(sum));
        salariesLabel.setVisible(true);
    }
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public void handleGoBackAction(javafx.event.ActionEvent actionEvent){
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menu_admin.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleGetTotalMoney(){
        Alert alert=new Alert(Alert.AlertType.WARNING);
        if(isNumeric(startDayTF.getText()) && isNumeric(endDayTF.getText()) && isNumeric(startMonthTF.getText()) && isNumeric(endMonthTF.getText()))
        {
            float sum = 0;
            int startDay,endDay,startMonth,endMonth;
            startDay = Integer.parseInt(startDayTF.getText());
            endDay = Integer.parseInt(endDayTF.getText());
            startMonth = Integer.parseInt(startMonthTF.getText());
            endMonth = Integer.parseInt(endMonthTF.getText());
            for(Transaction transaction : transactions){
                if(transaction.getDay() >= startDay && transaction.getDay() <= endDay &&
                        transaction.getMonth() >= startMonth
                        && transaction.getMonth() <= endMonth){
                    sum += transaction.getPayment();
                }
            }
            salariesLabel.setText(String.valueOf(sum));
            salariesLabel.setVisible(true);
        }
        else{
            alert.setContentText("Please enter only numbers.");
            alert.show();
        }

    }
}
