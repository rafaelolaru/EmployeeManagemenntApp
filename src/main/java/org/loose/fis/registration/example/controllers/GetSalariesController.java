package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    public void handleGetTotalMoney(){
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
}
