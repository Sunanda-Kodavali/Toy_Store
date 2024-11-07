package dci.j24e01.ToyStore.services;

import dci.j24e01.ToyStore.models.Product;

import java.util.List;

public interface ProductDAO {

    Product getProduct(int id);
    List<Product> getAllProducts();
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int id);

}
