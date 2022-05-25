package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
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

    public void handleAddTransaction() {
        Alert alert=new Alert(Alert.AlertType.WARNING);
        if (isNumeric(sumTF.getText()) && isNumeric(dayTF.getText()) && isNumeric(monthTF.getText())){

            int payment = Integer.parseInt(sumTF.getText());
            int day = Integer.parseInt(dayTF.getText());
            int month = Integer.parseInt(monthTF.getText());
            boolean salary = salaryCheckBox.isSelected();
            System.out.println(String.valueOf(payment) + String.valueOf(day) + String.valueOf(month) + String.valueOf(salary));
            Transaction transaction = new Transaction(payment, day, month, salary);
            TransactionService.addTransaction(transaction);
        }
        else {
            alert.setContentText("Please enter only numbers.");
            alert.show();
        }
    }
}
