package com.nashtech.kpi.entity;

import com.nashtech.kpi.model.Gender;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Getter
@Setter
@Table(name = "products")
public class ProductEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String productName;

    @NonNull
    private Long price;

    @NonNull
    private String size;

    private Integer quantity;

    private String promotion;

    private String description;

    private Boolean outOfStock = false;

    private Gender gender;

}
