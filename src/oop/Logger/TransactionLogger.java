package oop.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionLogger {

    private TransactionLogger() {
    }

    private static final String LOG_FILE_PATH = "transactions.log";

    public enum TransactionType {
        NEW_PRODUCT, UPDATE, DELETE, WITHDRAWAL, DEPOSIT
    }

    public static void logTransaction(String productCode,
            TransactionType transactionType, int quantity) {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(
                LOG_FILE_PATH, true))) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                    "yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            String logMessage = String.format("%s, %s, %s", formattedDateTime,
                    productCode, transactionType);
            if (transactionType == TransactionType.WITHDRAWAL || transactionType == TransactionType.DEPOSIT) {
                logMessage += ", " + quantity;
            }
            writer.write(logMessage);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error while logging transaction: " + e.
                    getMessage());
        }
    }
}
