package dci.j24e01.ToyStore.services;

import dci.j24e01.ToyStore.models.Product;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDAOImpl implements ProductDAO {

    private final Connection connection;

    public ProductDAOImpl(DBConnectionManager connectionManager) {
        this.connection = connectionManager.getConnection();
    }

    @Override
    public Product getProduct(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        Product product = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                product =  new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantityInStock"),
                        rs.getString("manufacturer"),
                        rs.getString("ageGroup"),
                        rs.getString("image"),
                        rs.getString("material"),
                        rs.getString("barcode"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("modified_at", LocalDateTime.class)
                );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;

    }

    @Override
    public List<Product> getAllProducts() {

        String sql = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();
        try {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Product product = new Product(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("quantityInStock"),
                    rs.getString("manufacturer"),
                    rs.getString("ageGroup"),
                    rs.getString("image"),
                    rs.getString("material"),
                    rs.getString("barcode"),
                    rs.getObject("created_at", LocalDateTime.class),
                    rs.getObject("modified_at", LocalDateTime.class)
            );
            products.add(product);
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    @Override
    public void addProduct(Product product) {
        String sql = "INSERT INTO products(name, category,description, price, quantityInStock," +
                "manufacturer, ageGroup, image, material, barcode) VALUES (?, ?, ?, ?, ?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.name());
            preparedStatement.setString(2, product.category());
            preparedStatement.setString(3, product.description());
            preparedStatement.setDouble(4, product.price());
            preparedStatement.setInt(5, product.quantityInStock());
            preparedStatement.setString(6, product.manufacturer());
            preparedStatement.setString(7, product.ageGroup());
            preparedStatement.setString(8, product.image());
            preparedStatement.setString(9, product.material());
            preparedStatement.setString(10, product.barcode());

            int affected = preparedStatement.executeUpdate();
            if(affected > 0) {
                System.out.println("--------------Product added successfully--------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateProduct(Product product) {

        String sql = "UPDATE products SET name = ?, category = ?, description = ?, price = ?, quantityInStock = ?, " +
                "manufacturer = ?, ageGroup = ?, image = ?, material = ?, barcode = ?, modified_at = ? WHERE id = ?";


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.name());
            preparedStatement.setString(2, product.category());
            preparedStatement.setString(3, product.description());
            preparedStatement.setDouble(4, product.price());
            preparedStatement.setInt(5, product.quantityInStock());
            preparedStatement.setString(6, product.manufacturer());
            preparedStatement.setString(7, product.ageGroup());
            preparedStatement.setString(8, product.image());
            preparedStatement.setString(9, product.material());
            preparedStatement.setString(10, product.barcode());
            preparedStatement.setTimestamp(11, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setLong(12, product.id());

            int affected = preparedStatement.executeUpdate();
            if(affected > 0) {
                System.out.println("--------------Product updated successfully--------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
