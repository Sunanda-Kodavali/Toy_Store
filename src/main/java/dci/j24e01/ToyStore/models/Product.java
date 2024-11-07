package dci.j24e01.ToyStore.models;

import java.time.LocalDateTime;

public record Product (Long id,
                       String name,
                       String category,
                       String description,
                       double price,
                       int quantityInStock,
                       String manufacturer,
                       String ageGroup,
                       String image,
                       String material,
                       String barcode,
                       LocalDateTime createdAt,
                       LocalDateTime modifiedAt){
}
