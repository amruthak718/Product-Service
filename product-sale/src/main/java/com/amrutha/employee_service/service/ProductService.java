package com.amrutha.employee_service.service;

import com.amrutha.employee_service.repository.ProductRepository;
import com.amrutha.employee_service.entity.Product;
import com.amrutha.employee_service.entity.Sale;
import com.amrutha.employee_service.model.ProductDTO;
import com.amrutha.employee_service.model.SaleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Product addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setDescription(productDTO.getDescription());

        if (productDTO.getSales() != null) {
            List<Sale> sales = new ArrayList<>();
            for (SaleDTO saleDTO : productDTO.getSales()) {
                Sale sale = mapSaleDTOToEntity(saleDTO,productDTO.getId());
                sales.add(sale);
            }
            product.setSales(sales);
        }

        return productRepository.save(product);
    }

    private Sale mapSaleDTOToEntity(SaleDTO saleDTO,int productId) {
        Sale sale = new Sale();
        sale.setId(saleDTO.getId());
        sale.setProductId(productId);
        sale.setQuantity(saleDTO.getQuantity());
        sale.setSaleDate(saleDTO.getSaleDate());
        return sale;
    }

    /**
     * Get all the products.
     *
     * @return the list of entities
     */
    public Page<Product> getAllProducts(int offset,int pageSize) {
        Page<Product> products = productRepository.findAll(PageRequest.of(offset, pageSize));
        return products;
    }


        /**
         * Get one product by ID.
         *
         * @param id the ID of the entity
         * @return the entity
         */
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    /**
     * Update a product.
     *
     * @param id the ID of the entity
     * @param updatedProduct the updated entity
     * @return the updated entity
     */
    public Product updateProduct(int id, Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setQuantity(updatedProduct.getQuantity());
            product.setDescription(updatedProduct.getDescription());
            return productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    /**
     * Delete the product by ID.
     *
     * @param id the ID of the entity
     */
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public double getTotalRevenue() {
        Double totalRevenue = jdbcTemplate.queryForObject("SELECT SUM(p.price * s.quantity) AS total_revenue\n"
                + "FROM product_sale.product p\n" + "JOIN product_sale.sale s ON p.id = s.product_id ",Double.class);
        return  totalRevenue;
    }

    public double getRevenueByProduct(int productId) {
        Double totalRevenueByProduct = jdbcTemplate.queryForObject("SELECT SUM(p.price * s.quantity) AS total_revenue\n"
                + "FROM product_sale.product p\n" + "JOIN product_sale.sale s ON p.id = s.product_id\n"
                + "WHERE s.product_id = ?",Double.class,productId);
        return totalRevenueByProduct;
    }

}
