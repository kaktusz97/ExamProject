package oop.controllers;

import java.util.List;
import oop.entities.Product;

/**
 *
 * @author Marci
 * @param <T>
 */
public interface ProductController<T extends Product> {

    public void addProduct(T product);

    public void updateProduct(T product);

    public void deleteProduct(String articleNumber);

    public T getProductByArticleNumber(String articleNumber);

    public List<T> getAllProducts();
}
