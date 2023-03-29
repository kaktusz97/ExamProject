package oop.views;

import oop.entities.Product;

/**
 *
 * @author Marci
 * @param <T>
 */
public interface ProductEventListener<T extends Product> {

    void productCreated(T p);

    void productUpdated(T p);

    void productDeleted(T p);
}
