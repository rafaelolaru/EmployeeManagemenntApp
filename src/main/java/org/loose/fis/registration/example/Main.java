package org.loose.fis.registration.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.loose.fis.registration.example.controllers.LogInController;
import org.loose.fis.registration.example.model.User;
import org.loose.fis.registration.example.services.UserService;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private Parent root;

    public Main(){}

    public static void main(String[] args) {

        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            UserService.loadUsersFromFile();

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            primaryStage.setTitle("Employee Management");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
