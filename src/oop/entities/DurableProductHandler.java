package oop.entities;

import java.util.List;
import oop.persistence.ProductController;
import oop.persistence.ProductControllerFactory;
import oop.utils.ProductType;

public class DurableProductHandler implements ProductHandler {

    private ProductController controller = ProductControllerFactory.
            getProductController(ProductType.DURABLE_PRODUCT);

    @Override
    public void insert(Product p) {
        controller.insert(p);
    }

    @Override
    public void update(Product p) {
        controller.update(p);
    }

    @Override
    public void delete(Product p) {
        controller.delete(p);
    }

    @Override
    public List<DurableProduct> getAllProducts() {
        return controller.getAllProducts();
    }

    @Override
    public Product getProductByArticleNumber(String articleNumber) {
        return controller.getProductByArticleNumber(articleNumber);
    }

    @Override
    public void deposit(Product product, int quantity) {
        controller.deposit(product, quantity);
    }

    @Override
    public void withdraw(Product product, int quantity) {
        controller.withdraw(product, quantity);
    }

}
