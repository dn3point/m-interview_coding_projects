package page.zhaoyuan.gds.main;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTest {
    @Test
    public void testGetTransactions() {
        String[] names = new String[] { "Alice", "Bob", "Charlie" };
        double[] pays = new double[] { 60.0, 120.0, 30.0 };
        List<Transaction> transactions = Main.getTransactions(names, pays);
        assertEquals(transactions.size(), 2);
        HashSet<String> transactionTexts = new HashSet<>();
        for (Transaction transaction : transactions) {
            transactionTexts.add(transaction.toString());
        }
        assertTrue(transactionTexts.contains("Charlie pays Bob $40.0"));
        assertTrue(transactionTexts.contains("Alice pays Bob $10.0"));

        names = new String[] { "Ali", "Zack" };
        pays = new double[] { 10.0, 0.0 };
        transactions = Main.getTransactions(names, pays);
        assertEquals(transactions.size(), 1);
        transactionTexts = new HashSet<>();
        for (Transaction transaction : transactions) {
            transactionTexts.add(transaction.toString());
        }
        assertTrue(transactionTexts.contains("Zack pays Ali $5.0"));
    }
}
