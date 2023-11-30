package com.nashtech.kpi.service;

import com.nashtech.kpi.dto.ProductDTO;
import com.nashtech.kpi.dto.UserDTO;
import com.nashtech.kpi.entity.ProductEntity;
import com.nashtech.kpi.entity.RoleEntity;
import com.nashtech.kpi.entity.UserEntity;
import com.nashtech.kpi.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDTO> findInStockProducts() {
        List<ProductEntity> product = productRepository.findByOutOfStock(true);
        return modelMapper.map(product, new TypeToken<List<ProductDTO>>() {}.getType());
    }

    @Transactional
    public List<ProductDTO> findAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductDTO.class))
                .toList();
    }

    public ProductDTO findProductById(int id) {
        return modelMapper.map(productRepository.findById(id), new TypeToken<ProductDTO>() {}.getType());
    }

    public ProductEntity saveProduct(ProductDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt principal = (Jwt) authentication.getPrincipal();
        Map<String, Object> claims = principal.getClaims();

        String createdBy = String.valueOf(claims.get("email"));
        ProductEntity entity;
        if (dto.getId() == null || productRepository.findById(dto.getId()).isEmpty()) {
            entity = modelMapper.map(dto, ProductEntity.class);
            entity.setCreatedBy(createdBy);
        } else {
            entity = productRepository.findById(dto.getId()).get();
            entity.setProductName(dto.getProductName());
            entity.setPrice(dto.getPrice());
            entity.setSize(dto.getSize());
            entity.setQuantity(dto.getQuantity());
            entity.setPromotion(dto.getPromotion());
            entity.setDescription(dto.getDescription());
            entity.setGender(dto.getGender());
            entity.setLastUpdatedBy(createdBy);
        }
        return productRepository.save(entity);
    }

    public void deleteById(int id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
        } else {
            throw new RuntimeException("Product not found");
        }
    }
}
