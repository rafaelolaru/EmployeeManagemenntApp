package org.loose.fis.registration.example.controllers;


import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.loose.fis.registration.example.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.registration.example.services.FileSystemService;
import org.loose.fis.registration.example.services.UserService;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;


public class LogInControllerTest extends ApplicationTest {
    public static final String TEST_USER = "testUser";
    public static final String TEST_PASSWORD = "testPassword";
    public LogInController controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-employee-register";
        FileSystemService.initApplicationHomeDirIfNeeded();
        UserService.loadUsersFromFile();
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        UserService.loadUsersFromFile();

        controller=new LogInController();

        controller.usernameField=new TextField();
        controller.passwordField=new PasswordField();
        controller.registrationMessage=new Text();

        controller.usernameField.setText(TEST_USER);
        controller.passwordField.setText(TEST_PASSWORD);

    }

    @Test
    public void testMissingUser(){

        controller.handleLogInAction();
        assertEquals("Wrong username or password", controller.registrationMessage.getText());
    }

    @Test
    public void testAddedUser() throws UsernameAlreadyExistsException {
        UserService.addUser(TEST_USER,TEST_PASSWORD,"","","");
        controller.handleLogInAction();
        assertEquals("Account found", controller.registrationMessage.getText());
    }

}