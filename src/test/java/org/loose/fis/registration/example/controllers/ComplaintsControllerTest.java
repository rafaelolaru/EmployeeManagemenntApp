package org.loose.fis.registration.example.controllers;

import javafx.scene.control.TextArea;
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

public class ComplaintsControllerTest extends ApplicationTest {
    public String TEST_COMPLAINT=new String("This is a test");
    public String TEST_COMPLAINT2=new String("This is the second test");
    public static final String TEST_USERNAME = "testUser";
    public static final String TEST_PASSWORD = "testPassword";
    public static User TEST_USER;
    ComplaintsController controller;

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

        controller=new ComplaintsController();
        controller.textComplaints=new TextArea();
        controller.textComplaints.setText(TEST_COMPLAINT);


    }

    @Test
    public void testOneComplaint() throws UsernameAlreadyExistsException {
        UserService.addUser(TEST_USERNAME,TEST_PASSWORD,"","","");
        TEST_USER=UserService.getUsername(TEST_USERNAME);
        controller.setUser(TEST_USER);
        controller.onSubmit();
        assertEquals(controller.textComplaints.getText(), UserService.getUsername(TEST_USERNAME).getComplaints().getMessage());
    }
    @Test
    public void testTwoComplaints() throws UsernameAlreadyExistsException {
        UserService.addUser(TEST_USERNAME,TEST_PASSWORD,"","","");
        TEST_USER=UserService.getUsername(TEST_USERNAME);
        controller.setUser(TEST_USER);
        controller.onSubmit();
        controller.textComplaints.setText(TEST_COMPLAINT2);
        controller.onSubmit();
        assertEquals(controller.textComplaints.getText(), UserService.getUsername(TEST_USERNAME).getComplaints().getMessage());
    }
}