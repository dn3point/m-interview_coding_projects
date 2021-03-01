package page.zhaoyuan.gds.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            System.out.println("Start application");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Number of people: ");
            int n = scanner.nextInt();
            String[] names = new String[n];
            double[] pays = new double[n];
            for (int i = 0; i < n; i++) {
                System.out.print("Name " + (i + 1) + ": ");
                names[i] = scanner.next();
                System.out.print("Pay: ");
                pays[i] = scanner.nextDouble();
            }
            List<Transaction> transactionList = getTransactions(names, pays);
            for (Transaction transaction : transactionList) {
                System.out.println(transaction.toString());
            }
            System.out.println("Number of transactions: " + transactionList.size());
        } catch (Exception e) {
            System.out.println("Invalid input given!");
        } finally {
            System.out.println("Done");
        }
    }

    public static List<Transaction> getTransactions(String[] names, double[] pays) {
        List<Transaction> transactionList = new ArrayList<>();
        if (names == null || pays == null || names.length != pays.length || names.length == 0) {
            return transactionList;
        }

        double sum = 0;
        for (double p : pays) {
            if (p < 0) {
                return transactionList;
            }
            sum += p;
        }

        double average = sum / (1.0 * pays.length);

        Record[] balance = new Record[pays.length];
        for (int i = 0; i < pays.length; i++) {
            balance[i] = new Record(names[i], pays[i] - average);
        }

        Arrays.sort(balance);
        int l = 0;
        int r = pays.length - 1;

        while (l < r) {
            if (balance[l].getAmount() == 0.0) {
                break;
            }
            double lAbsAmount = Math.abs(balance[l].getAmount());
            double rAbsAmount = Math.abs(balance[r].getAmount());
            if (lAbsAmount == rAbsAmount) {
                transactionList.add(new Transaction(balance[l].getName(), balance[r].getName(), lAbsAmount));
                l++;
                r--;
            } else if (lAbsAmount < rAbsAmount) {
                transactionList.add(new Transaction(balance[l].getName(), balance[r].getName(), lAbsAmount));
                balance[r].setAmount(rAbsAmount - lAbsAmount);
                l++;
            } else {
                transactionList.add(new Transaction(balance[l].getName(), balance[r].getName(), rAbsAmount));
                balance[l].setAmount(rAbsAmount - lAbsAmount);
                r--;
            }
        }

        return transactionList;
    }
}
