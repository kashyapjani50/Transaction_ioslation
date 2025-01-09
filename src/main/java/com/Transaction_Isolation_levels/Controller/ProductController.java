package com.Transaction_Isolation_levels.Controller;

import com.Transaction_Isolation_levels.Entity.Product;
import com.Transaction_Isolation_levels.Services.ProductReadService;
import com.Transaction_Isolation_levels.Services.ProductUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductUpdateService productUpdateService;

    @Autowired
    private ProductReadService productReadService;

    // Endpoint to create a new product
    @PostMapping("/create")
    public String createProduct(@RequestBody Product product) {
        Product savedProduct = productUpdateService.createProduct(product);
        return "Product created successfully with ID: " + savedProduct.getId();
    }

    // Endpoint to update product price (Transaction 1)
    @PutMapping("/first/{id}/{newPrice}")
    public String updateProductPrice(@PathVariable Long id, @PathVariable Double newPrice) {
        productUpdateService.UpdateProductPrice1(id, newPrice);
        return "Update transaction started for product " + id + ". Check the console for results.";
    }

    @PutMapping("/second/{id}/{newPrice}")
    public String updateProductPrice1(@PathVariable Long id, @PathVariable Double newPrice) {
        productUpdateService.UpdateProductPrice2(id, newPrice);
        return "Update transaction started for product " + id + ". Check the console for results.";
    }



//     Endpoint to read product details (Transaction 2)
    @GetMapping("/{id}")
    public String readProductDetails(@PathVariable Long id) {
       return productReadService.displayProductDetails(id);
    }
}
