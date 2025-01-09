package com.Transaction_Isolation_levels.Services;

import com.Transaction_Isolation_levels.Entity.Product;
import com.Transaction_Isolation_levels.Repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductReadService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager em;

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public String displayProductDetails(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        System.out.println("Product Name: " + product.getName());
        System.out.println("Product Price: " + product.getPrice());


        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Product product1 = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        System.out.println("Product Name: " + product1.getName());
        System.out.println("Product Price: " + product1.getPrice());

        return "Price : " + product.getPrice();

    }




}
