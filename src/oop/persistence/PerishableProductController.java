package oop.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import oop.Logger.TransactionLogger;
import oop.entities.PerishableProduct;
import oop.exceptions.PersistenceException;
import oop.exceptions.VerificationException;

public class PerishableProductController implements ProductController<PerishableProduct> {

    private static Connection conn;

    static {
        conn = DBConnection.getDatabaseConnection();
    }

    @Override
    public void insert(PerishableProduct product) {
        String storedProc
                = "{call insert_perishable_product (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try {
            CallableStatement statement = conn.prepareCall(storedProc);
            statement.setString(1, product.getArticleNumber());
            statement.setString(2, product.getName());
            statement.setString(3, product.getBrand());
            statement.setString(4, product.getFamily());
            statement.setInt(5, product.getNettoPrice());
            statement.setInt(6, product.getTaxId());
            statement.setInt(7, product.getQuantity());
            statement.setString(8, product.getAmountUnits());
            statement.setInt(9, product.getCriticalQuantity());
            statement.setDate(10, product.getExpirationDate());
            statement.setDate(11, product.getProductionDate());
            statement.executeUpdate();
            TransactionLogger.logTransaction(product.getArticleNumber(),
                    TransactionLogger.TransactionType.NEW_PRODUCT, product.
                            getQuantity());
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new PersistenceException(e.getMessage());
        } catch (SQLException e) {
            throw new PersistenceException("insert failed");
        }
    }

    @Override
    public void update(PerishableProduct product
    ) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "UPDATE perishable_product SET name = ?, brand = ?, family = ?, netto_price = ?, tax_id = ?,"
                    + " quantity = ?, amount_units = ?, critical_quantity = ?, expiration_date =?,"
                    + " production_date = ? WHERE article_number = ?");
            statement.setString(1, product.getName());
            statement.setString(2, product.getBrand());
            statement.setString(3, product.getFamily());
            statement.setInt(4, product.getNettoPrice());
            statement.setInt(5, product.getTaxId());
            statement.setInt(6, product.getQuantity());
            statement.setString(7, product.getAmountUnits());
            statement.setInt(8, product.getCriticalQuantity());
            statement.setDate(9, (Date) product.getExpirationDate());
            statement.setDate(10, (Date) product.getProductionDate());
            statement.setString(11, product.getArticleNumber());
            statement.executeUpdate();
            TransactionLogger.logTransaction(product.getArticleNumber(),
                    TransactionLogger.TransactionType.UPDATE, product.
                            getQuantity());
        } catch (SQLException e) {
            throw new PersistenceException("update failed");
        }
    }

    @Override
    public void delete(PerishableProduct product
    ) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM perishable_product WHERE article_number = ?");
            statement.setString(1, product.getArticleNumber());
            statement.executeUpdate();
            TransactionLogger.logTransaction(product.getArticleNumber(),
                    TransactionLogger.TransactionType.DELETE, product.
                            getQuantity());
        } catch (SQLException e) {
            throw new PersistenceException("delete failed");
        }
    }

    @Override
    public PerishableProduct getProductByArticleNumber(String articleNumber
    ) {
        PerishableProduct product = null;
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT * FROM perishable_product WHERE article_number = ?");
            statement.setString(1, articleNumber);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                product = new PerishableProduct(
                        result.getString("article_number"),
                        result.getString("name"),
                        result.getString("brand"),
                        result.getString("family"),
                        result.getInt("netto_price"),
                        result.getInt("tax_id"),
                        result.getInt("quantity"),
                        result.getString("amount_units"),
                        result.getInt("critical_quantity"),
                        result.getDate("expiration_date"),
                        result.getDate("production_date")
                );
            }
        } catch (SQLException e) {
            throw new PersistenceException("select failed");
        } catch (VerificationException ex) {
            throw new PersistenceException("parameter verification failed");
        }
        return product;
    }

    @Override
    public List<PerishableProduct> getAllProducts() {
        List<PerishableProduct> products = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT * FROM perishable_product");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                PerishableProduct product = new PerishableProduct(
                        result.getString("article_number"),
                        result.getString("name"),
                        result.getString("brand"),
                        result.getString("family"),
                        result.getInt("netto_price"),
                        result.getInt("tax_id"),
                        result.getInt("quantity"),
                        result.getString("amount_units"),
                        result.getInt("critical_quantity"),
                        result.getDate("expiration_date"),
                        result.getDate("production_date")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            throw new PersistenceException("select failed");
        } catch (VerificationException ex) {
            throw new PersistenceException("parameter verification failed");
        }
        return products;
    }

    @Override
    public void deposit(PerishableProduct product, int quantity) {
        int newQuantity = product.getQuantity() + quantity;
        updateQuantity(product, newQuantity);
        TransactionLogger.logTransaction(product.getArticleNumber(),
                TransactionLogger.TransactionType.DEPOSIT, product.
                        getQuantity());
    }

    @Override
    public void withdraw(PerishableProduct product, int quantity) {
        int newQuantity = product.getQuantity() - quantity;
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Cannot withdraw more than the available quantity.");
        }
        updateQuantity(product, newQuantity);
        TransactionLogger.logTransaction(product.getArticleNumber(),
                TransactionLogger.TransactionType.WITHDRAWAL, product.
                        getQuantity());
    }

    private void updateQuantity(PerishableProduct product, int newQuantity) {
        String sql = "UPDATE perishable_product SET quantity = ? WHERE article_number = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newQuantity);
            stmt.setString(2, product.getArticleNumber());
            stmt.executeUpdate();
            product.setQuantity(newQuantity);
        } catch (SQLException ex) {
            throw new PersistenceException("Cannot update quantity");
        } catch (VerificationException ex) {
            throw new PersistenceException("Cannot set quantity");
        }
    }

}
