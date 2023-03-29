package oop.views;

import oop.entities.Product;
import oop.entities.ProductHandler;

/**
 *
 * @author Marci
 */
public interface ProductHandlingStrategy {

    void save(ProductHandler handler, Product p);

    void notify(Product p, ProductEventListener l);

}
