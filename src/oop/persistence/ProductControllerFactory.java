package oop.persistence;

import oop.utils.ProductType;

public class ProductControllerFactory {

    private ProductControllerFactory() {
    }

    public static ProductController getProductController(ProductType type) {
        ProductController result = null;
        switch (type) {
            case DURABLE_PRODUCT:
                result = new DurableProductController();
                break;
            case PERISHABLE_PRODUCT:
                result = new PerishableProductController();
                break;
        }
        return result;
    }

}
