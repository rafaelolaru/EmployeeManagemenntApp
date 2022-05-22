package org.loose.fis.registration.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;
import org.apache.commons.io.FileUtils;
import org.loose.fis.registration.example.exceptions.CouldNotWriteTransactionsException;
import org.loose.fis.registration.example.exceptions.CouldNotWriteUsersException;
import org.loose.fis.registration.example.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.registration.example.model.Transaction;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class TransactionService {
    public static List<Transaction> transactions;
    private static final Path TRANSACTIONS_PATH  = FileSystemService.getPathToFile("config","transactions.json");

    public static void loadTransactionsFromFile() throws IOException{
        if (!Files.exists(TRANSACTIONS_PATH)){
            File file = new File(String.valueOf(TRANSACTIONS_PATH.toFile()));
            FileUtils.copyURLToFile(TransactionService.class.getClassLoader().getResource("transactions.json"), TRANSACTIONS_PATH.toFile());
        }
        System.out.println(TRANSACTIONS_PATH);

        ObjectMapper objectMapper = new ObjectMapper();

        transactions = objectMapper.readValue(TRANSACTIONS_PATH.toFile(), new TypeReference<List<Transaction>>() {});
    }
    public static void addTransaction(Transaction transaction){
        transactions.add(transaction);
        persistTransactions();
    }

    public static void persistTransactions(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(TRANSACTIONS_PATH.toFile(), transactions);
        }catch (IOException e){
            throw new CouldNotWriteTransactionsException();
        }
    }
}
