package com.amrutha.employee_service.controller;

import com.amrutha.employee_service.entity.Product;
import com.amrutha.employee_service.model.ProductDTO;
import com.amrutha.employee_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProductController {


    @Autowired
    private ProductService productService;

    /**
     * Create a new product.
     *
     * @param productDTO the product to create
     * @return the ResponseEntity with status 200 (OK) and with body of the new product
     */
    @PostMapping("/product")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDTO) {
        Product newProduct = productService.addProduct(productDTO);
        return ResponseEntity.ok(newProduct);
    }

    /**
     * Get all products.
     *
     * @return the ResponseEntity with status 200 (OK) and with body of the list of products
     */
    @GetMapping("/pagination/{offset}/{pageSize}")
    public Page<Product> getAllProducts(@PathVariable int offset, @PathVariable int pageSize) {
        return productService.getAllProducts(offset,pageSize);
    }

    /**
     * Get a product by ID.
     *
     * @param id the ID of the product to get
     * @return the ResponseEntity with status 200 (OK) and with body of the product, or with status 404 (Not Found) if the product does not exist
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Update a product by ID.
     *
     * @param id the ID of the product to update
     * @param product the updated product
     * @return the ResponseEntity with status 200 (OK) and with body of the updated product, or with status 404 (Not Found) if the product does not exist
     */
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Delete a product by ID.
     *
     * @param id the ID of the product to delete
     * @return the ResponseEntity with status 200 (OK) and with body of the message "Product deleted successfully"
     */
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }


    @GetMapping("/revenue/total")
    public double getTotalRevenue() {
        return productService.getTotalRevenue();
    }

    @GetMapping("/revenue/{productId}")
    public double getRevenueByProduct(@PathVariable int productId) {
        return productService.getRevenueByProduct(productId);
    }
}
