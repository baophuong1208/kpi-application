package com.nashtech.kpi.controller;

import com.nashtech.kpi.dto.ProductDTO;
import com.nashtech.kpi.dto.UserDTO;
import com.nashtech.kpi.entity.ProductEntity;
import com.nashtech.kpi.service.ProductService;
import com.nashtech.kpi.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/products")
    public ResponseEntity<?> getAllProduct() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") int id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/products")
    public ResponseEntity<?> upsertProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.saveProduct(productDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
