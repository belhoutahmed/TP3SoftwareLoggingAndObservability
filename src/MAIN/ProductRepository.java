package MAIN;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private Map<String, Product> products = new HashMap<>();

    public void addProduct(Product product) throws Exception {
        if (products.containsKey(product.getId())) {
            throw new Exception("Product with the same ID already exists.");
        }
        products.put(product.getId(), product);
    }

    public Product fetchProductById(String id) throws Exception {
        if (!products.containsKey(id)) {
            throw new Exception("No product found with the provided ID.");
        }
        return products.get(id);
    }

    public void deleteProductById(String id) throws Exception {
        if (!products.containsKey(id)) {
            throw new Exception("No product found with the provided ID.");
        }
        products.remove(id);
    }

    public void updateProduct(Product updatedProduct) throws Exception {
        if (!products.containsKey(updatedProduct.getId())) {
            throw new Exception("No product found with the provided ID.");
        }
        products.put(updatedProduct.getId(), updatedProduct);
    }

    public Map<String, Product> getAllProducts() {
        return products;
    }
}
