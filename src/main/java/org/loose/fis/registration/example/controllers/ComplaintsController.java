package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;


public class ComplaintsController extends LogInController {
    @FXML
    TextArea textComplaints;
    public void onSubmit(){
        System.out.println(textComplaints.getText());
    }
}

