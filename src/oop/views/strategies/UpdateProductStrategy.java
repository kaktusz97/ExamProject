package oop.views.strategies;

import oop.entities.Product;
import oop.entities.ProductHandler;
import oop.views.ProductEventListener;
import oop.views.ProductHandlingStrategy;

public class UpdateProductStrategy implements ProductHandlingStrategy {

    @Override
    public void save(ProductHandler handler, Product p) {
        handler.update(p);
    }

    @Override
    public void notify(Product p, ProductEventListener l) {
        l.productUpdated(p);
    }

}
