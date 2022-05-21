package org.loose.fis.registration.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;
import org.apache.commons.io.FileUtils;
import org.loose.fis.registration.example.exceptions.CouldNotWriteUsersException;
import org.loose.fis.registration.example.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.registration.example.model.User;

import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class UserService {

    public static List<User> users;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("config", "users.json");

    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(USERS_PATH)) {
            File file=new File(String.valueOf(USERS_PATH.toFile()));
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("users.json"), USERS_PATH.toFile());
        }

        System.out.println(USERS_PATH);//shows path for json

        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
    }

    public static void addUser(String username, String password, String role,String full_name,String phone) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        users.add(new User(username, encodePassword(username, password), role,full_name,phone));
        persistUsers();
    }

    public static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : users) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }
    public static User checkAdmin(String code) {
        for (User user : users) {
            if (Objects.equals("Admin", user.getRole()) && Objects.equals(code, user.getCode()))
               return user;
        }
        return null;
    }
    public static boolean checkPasswordForUser(String username,String password){
        password=encodePassword(username,password);
        for (User user : users) {
            if (Objects.equals(username, user.getUsername()))
                if(Objects.equals(password,user.getPassword()))
                    return true;
        }
        return false;
    }

    private static void persistUsers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    public static User getUsername(String name){
        for (User user : users) {
            if (Objects.equals(name, user.getUsername()))
                return user;
        }
        return null;

    }

    public static ArrayList getFullName(String role,String code){

        ArrayList nameList=new ArrayList(users.size());
        for(User user : users){
            if(role.equals(user.getRole())&&code.equals(user.getCode()))
                nameList.add(user.getFull_name());
        }
        Collections.sort(nameList);
        return nameList;
    }
    public static User checkUserFullName(String Name){
        for (User user : users) {
            if (Objects.equals(Name, user.getFull_name()))
                return user;
        }
        return null;
    }

    public static boolean checkCode(String code) {
        for (User user : users) {
            if (Objects.equals(code, user.getCode()))
                return true;
        }
        return false;
    }
    public static void setCode(User userCode) {
        for (User user : users) {
            if (Objects.equals(userCode.getUsername(), user.getUsername()))
                user.setCode(userCode.getCode());
        }
        persistUsers();
    }



}
