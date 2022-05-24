package org.loose.fis.registration.example.controllers;


import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.loose.fis.registration.example.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.registration.example.model.User;
import org.loose.fis.registration.example.services.FileSystemService;
import org.loose.fis.registration.example.services.UserService;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class CodeControllerTest extends ApplicationTest {
    public static final String TEST_USER = "testUser";
    public static final String TEST_PASSWORD = "testPassword";
    public static final String TEST_CODE="testCode";
    public CodeController controller;


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

        controller=new CodeController();
        controller.setUserCode(TEST_CODE);
        controller.roleMessage=new Text();
        controller.codeMessage=new Text();
        controller.codeField=new TextField();

        controller.codeField.setText(TEST_CODE);

    }

    @Test
    public void testCodeAdmin(){
        controller.setUserRole("Admin");
        controller.handleInsertAction();
        assertEquals("Code created", controller.codeMessage.getText());
    }
    @Test
    public void testCodeAdminExists() throws UsernameAlreadyExistsException {
        UserService.addUser(TEST_USER+2,TEST_PASSWORD+2,"Admin","","");
        UserService.getUsername(TEST_USER+2).setCode(TEST_CODE);

        controller.setUserRole("Admin");
        controller.handleInsertAction();
        assertEquals("Code already exists", controller.codeMessage.getText());
    }

    @Test
    public void testCodeClient() throws UsernameAlreadyExistsException {
        controller.setUserRole("Client");
        UserService.addUser(TEST_USER+2,TEST_PASSWORD+2,"Admin","","");
        UserService.getUsername(TEST_USER+2).setCode(TEST_CODE);
        controller.handleInsertAction();
        assertEquals("Code found", controller.codeMessage.getText());
    }


}