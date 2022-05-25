package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.loose.fis.registration.example.exceptions.LunaInexistenta;
import org.loose.fis.registration.example.exceptions.ZiInexistenta;
import org.loose.fis.registration.example.model.Transaction;
import org.loose.fis.registration.example.services.TransactionService;

public class TransactionMenuController {

    @FXML
    private TextField sumTF;

    @FXML
    private TextField dayTF;

    @FXML
    private TextField monthTF;

    @FXML
    private CheckBox salaryCheckBox;

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

    public void handleAddTransaction() throws ZiInexistenta, LunaInexistenta {
        Alert alert=new Alert(Alert.AlertType.WARNING);
        if (isNumeric(sumTF.getText()) && isNumeric(dayTF.getText()) && isNumeric(monthTF.getText())){
            int payment = Integer.parseInt(sumTF.getText());
            int day = Integer.parseInt(dayTF.getText());
            int month = Integer.parseInt(monthTF.getText());
            boolean salary = salaryCheckBox.isSelected();
            Transaction transaction = new Transaction(payment, day, month, salary);
            try {
                transaction.verificaDate();
                TransactionService.addTransaction(transaction);
            } catch (Exception e){
                alert.setContentText("Please enter a valid date");
                alert.show();
            }
        }
        else {
            alert.setContentText("Please enter only numbers.");
            alert.show();
        }
    }
}
