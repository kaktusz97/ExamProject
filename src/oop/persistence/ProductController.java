package oop.persistence;

import java.util.List;
import oop.entities.Product;

/**
 *
 * @author Marci
 * @param <T>
 */
public interface ProductController<T extends Product> {

    public void insert(T product);

    public void update(T product);

    public void delete(T product);

    public T getProductByArticleNumber(String articleNumber);

    public List<T> getAllProducts();

    public void deposit(T product, int quantity);

    public void withdraw(T product, int quantity);
}
