package com.nashtech.kpi.dto;

import com.nashtech.kpi.model.Gender;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer id;
    private String productName;
    private Long price;
    private String size;
    private Integer quantity;
    private String promotion;
    private String description;
    private Boolean outOfStock;
    private Gender gender;

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", size='" + size + '\'' +
                ", quantity=" + quantity +
                ", promotion='" + promotion + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
