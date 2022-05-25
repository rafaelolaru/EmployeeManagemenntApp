package org.loose.fis.registration.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.loose.fis.registration.example.exceptions.CouldNotWriteTransactionsException;
import org.loose.fis.registration.example.exceptions.LunaInexistenta;
import org.loose.fis.registration.example.exceptions.ZiInexistenta;
import org.loose.fis.registration.example.model.Transaction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TransactionService {

    public static List<Transaction> transactions;
    public static final Path TRANS_PATH = FileSystemService.getPathToFile("config", "transactions.json");

    public static void loadTransactionsFromFile() throws IOException {

        /*if (!Files.exists(TRANS_PATH)) {
            File file=new File(String.valueOf(TRANS_PATH.toFile()));
            FileUtils.copyURLToFile(TransactionService.class.getClassLoader().getResource("transactions.json"), TRANS_PATH.toFile());
        }*/

        if (!Files.exists(TRANS_PATH)) {
            File file=new File(String.valueOf(TRANS_PATH.toFile()));
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("users.json"), TRANS_PATH.toFile());
        }

        System.out.println(TRANS_PATH);//shows path for json

        ObjectMapper objectMapper = new ObjectMapper();

        transactions = objectMapper.readValue(TRANS_PATH.toFile(), new TypeReference<List<Transaction>>() {
        });
    }
    public static void addTransaction(Transaction transaction) throws LunaInexistenta, ZiInexistenta {
        transactions.add(transaction);
        persistTransactions();
    }


    public static void persistTransactions(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(TRANS_PATH.toFile(), transactions);
        }catch (IOException e){
            throw new CouldNotWriteTransactionsException();
        }
    }
}
