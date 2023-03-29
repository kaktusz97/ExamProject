package oop.controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oop.entities.PerishableProduct;
import oop.exceptions.VerificationException;
import oop.persistence.DBConnection;

public class PerishableProductController implements ProductController<PerishableProduct> {

    private static Connection conn;

    static {
        conn = DBConnection.getDatabaseConnection();
    }

    @Override
    public void addProduct(PerishableProduct product) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO perishable_product (article_number, name, brand, family, netto_price, tax_id,"
                    + " quantity, amount_units, critical_quantity, expiration_date, production_date)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, product.getArticleNumber());
            statement.setString(2, product.getName());
            statement.setString(3, product.getBrand());
            statement.setString(4, product.getFamily());
            statement.setInt(5, product.getNettoPrice());
            statement.setInt(6, product.getTaxId());
            statement.setInt(7, product.getQuantity());
            statement.setString(8, product.getAmountUnits());
            statement.setInt(9, product.getCriticalQuantity());
            statement.setDate(10, (Date) product.getExpirationDate());
            statement.setDate(11, (Date) product.getProductionDate());
            statement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public void updateProduct(PerishableProduct product) {
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
        } catch (SQLException e) {
        }
    }

    @Override
    public void deleteProduct(String articleNumber) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM perishable_product WHERE article_number = ?");
            statement.setString(1, articleNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public PerishableProduct getProductByArticleNumber(String articleNumber) {
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
        } catch (VerificationException ex) {
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
        } catch (VerificationException ex) {
        }
        return products;
    }

}
