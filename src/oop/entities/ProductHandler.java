package oop.entities;

import java.util.List;

/**
 *
 * @author Marci
 * @param <T>
 */
public interface ProductHandler<T extends Product> {

    void insert(T product);

    void update(T product);

    void delete(T product);

    T getProductByArticleNumber(String articleNumber);

    List<T> getAllProducts();

    public void deposit(T product, int quantity);

    public void withdraw(T product, int quantity);
}
