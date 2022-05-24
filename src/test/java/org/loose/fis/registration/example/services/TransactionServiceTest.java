package org.loose.fis.registration.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.loose.fis.registration.example.model.Transaction;
import org.loose.fis.registration.example.model.User;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.assertNotEquals;

public class TransactionServiceTest extends TestCase {
    public Transaction[] TEST_TRANSACTIONS=new Transaction[2];

    @BeforeClass
    public static void setupClass() {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileSystemService.initApplicationHomeDirIfNeeded();
    }

    @Before
    public void setUp() throws IOException {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        TEST_TRANSACTIONS[0]=new Transaction(1,2,3);
        TEST_TRANSACTIONS[1]=new Transaction(2,3,4);
    }

    @Test
    public void testCopyDefaultFileIfNotExists() throws Exception {
        TransactionService.loadTransactionsFromFile();
        assertTrue(Files.exists(TransactionService.TRANS_PATH));
    }

    @Test
    public void testLoadUsersFromFile() throws Exception {
        TransactionService.loadTransactionsFromFile();
        assertNotNull(TransactionService.transactions);
        assertEquals(0, TransactionService.transactions.size());
    }

    @Test
    public void testAddOneTransaction() throws Exception {
        TransactionService.loadTransactionsFromFile();
        TransactionService.addTransaction(TEST_TRANSACTIONS[0]);
        assertNotNull(UserService.users);
        assertEquals(1, UserService.users.size());
    }

    @Test
    public void testAddTwoTransactions() throws Exception {
        TransactionService.loadTransactionsFromFile();
        TransactionService.addTransaction(TEST_TRANSACTIONS[0]);
        TransactionService.addTransaction(TEST_TRANSACTIONS[1]);
        assertNotNull(UserService.users);
        assertEquals(2, UserService.users.size());
    }


    @Test
    public void testAddOneUserIsPersisted() throws Exception {
        TransactionService.loadTransactionsFromFile();
        TransactionService.addTransaction(TEST_TRANSACTIONS[0]);
        List<Transaction> transactions = new ObjectMapper().readValue(TransactionService.TRANS_PATH.toFile(), new TypeReference<List<Transaction>>() {
        });
        assertNotNull(transactions);
        assertEquals(1, transactions.size());
    }

    @Test
    public void testAddTwoUserArePersisted() throws Exception {
        TransactionService.loadTransactionsFromFile();
        TransactionService.addTransaction(TEST_TRANSACTIONS[0]);
        TransactionService.addTransaction(TEST_TRANSACTIONS[1]);
        List<Transaction> transactions = new ObjectMapper().readValue(TransactionService.TRANS_PATH.toFile(), new TypeReference<List<Transaction>>() {
        });
        assertNotNull(transactions);
        assertEquals(1, transactions.size());
    }



}