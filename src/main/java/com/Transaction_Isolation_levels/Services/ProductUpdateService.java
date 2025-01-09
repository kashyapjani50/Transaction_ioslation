package com.Transaction_Isolation_levels.Services;

import com.Transaction_Isolation_levels.Entity.Product;
import com.Transaction_Isolation_levels.Repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
//@EnableAsync
public class ProductUpdateService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
//    ////////////////////////////// Read Uncommitted ///////////////////////////////

//    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
//    public void updateProductPrice(Long id, Double newPrice) {
//        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
//        product.setPrice(newPrice);
//        productRepository.save(product);
//        entityManager.flush();
//
//        // Simulate delay to keep transaction open
//        try {
//            System.out.println("Updating product price but delaying commit...");
//            Thread.sleep(10000);
//           // 10 seconds delay
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//        System.out.println(10/0);
//
//    }

//    ////////////////////////////// Read committed ///////////////////////////////
//    @Transactional(isolation = Isolation.READ_COMMITTED)
//    public void updateProductPrice(Long id, Double newPrice) {
//        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
//        product.setPrice(newPrice);
//        productRepository.save(product);
//        entityManager.flush();
//
//        // Simulate delay to keep transaction open
//        try {
//            System.out.println("Updating product price but delaying commit...");
//            Thread.sleep(10000);
//            // 10 seconds delay
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//        //System.out.println(10/0);
//
//    }



//    //////////////////////////// REPEATABLE READ ///////////////////////////////
 //   @Async
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void UpdateProductPrice(Long id, Double newPrice) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setPrice(newPrice);
        productRepository.save(product);
//        entityManager.flush();

//        System.out.println(product.getPrice());
//        System.out.println(product.getName());

        // Simulate delay to keep transaction open
//        try {
//           // System.out.println("Updating product price but delaying commit...");
//            Thread.sleep(0);
//            // 10 seconds delay
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
        //System.out.println(10/0);



    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void UpdateProductPrice1(Long id, Double newPrice) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setPrice(newPrice);
        productRepository.save(product);
    }
}
