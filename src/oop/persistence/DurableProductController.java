package oop.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import oop.Logger.TransactionLogger;
import oop.entities.DurableProduct;
import oop.exceptions.VerificationException;
import oop.exceptions.PersistenceException;

public class DurableProductController implements ProductController<DurableProduct> {

    private static Connection conn;

    static {
        conn = DBConnection.getDatabaseConnection();
    }

    @Override
    public void insert(DurableProduct product) {
        String storedProc
                = "{call insert_durable_product (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
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
            statement.setInt(10, product.getWarrantyPeriod());
            statement.setDouble(11, product.getGrossWeight());
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
    public void update(DurableProduct product) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "UPDATE durable_product SET name = ?, brand = ?, family = ?, netto_price = ?, tax_id = ?,"
                    + " quantity = ?, amount_units = ?, critical_quantity = ?, warranty_period = ?,"
                    + " gross_weight = ? WHERE article_number = ?");
            statement.setString(1, product.getName());
            statement.setString(2, product.getBrand());
            statement.setString(3, product.getFamily());
            statement.setInt(4, product.getNettoPrice());
            statement.setInt(5, product.getTaxId());
            statement.setInt(6, product.getQuantity());
            statement.setString(7, product.getAmountUnits());
            statement.setInt(8, product.getCriticalQuantity());
            statement.setInt(9, product.getWarrantyPeriod());
            statement.setDouble(10, product.getGrossWeight());
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
    public void delete(DurableProduct product) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM durable_product WHERE article_number = ?");
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
    public DurableProduct getProductByArticleNumber(String articleNumber) {
        DurableProduct product = null;
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT * FROM durable_product WHERE article_number = ?");
            statement.setString(1, articleNumber);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                product = new DurableProduct(
                        result.getString("article_number"),
                        result.getString("name"),
                        result.getString("brand"),
                        result.getString("family"),
                        result.getInt("netto_price"),
                        result.getInt("tax_id"),
                        result.getInt("quantity"),
                        result.getString("amount_units"),
                        result.getInt("critical_quantity"),
                        result.getInt("warranty_period"),
                        result.getInt("gross_weight")
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
    public List<DurableProduct> getAllProducts() {
        List<DurableProduct> products = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT * FROM durable_product");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                DurableProduct product = new DurableProduct(
                        result.getString("article_number"),
                        result.getString("name"),
                        result.getString("brand"),
                        result.getString("family"),
                        result.getInt("netto_price"),
                        result.getInt("tax_id"),
                        result.getInt("quantity"),
                        result.getString("amount_units"),
                        result.getInt("critical_quantity"),
                        result.getInt("warranty_period"),
                        result.getInt("gross_weight")
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
    public void deposit(DurableProduct product, int quantity) {
        int newQuantity = product.getQuantity() + quantity;
        updateQuantity(product, newQuantity);
        TransactionLogger.logTransaction(product.getArticleNumber(),
                TransactionLogger.TransactionType.DEPOSIT, product.
                        getQuantity());
    }

    @Override
    public void withdraw(DurableProduct product, int quantity) {
        int newQuantity = product.getQuantity() - quantity;
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Cannot withdraw more than the available quantity.");
        }
        updateQuantity(product, newQuantity);
        TransactionLogger.logTransaction(product.getArticleNumber(),
                TransactionLogger.TransactionType.WITHDRAWAL, product.
                        getQuantity());
    }

    private void updateQuantity(DurableProduct product, int newQuantity) {
        String sql = "UPDATE durable_product SET quantity = ? WHERE article_number = ?";
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
