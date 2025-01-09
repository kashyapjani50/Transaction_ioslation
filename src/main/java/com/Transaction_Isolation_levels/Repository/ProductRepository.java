package com.Transaction_Isolation_levels.Repository;

import com.Transaction_Isolation_levels.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Basic CRUD methods are inherited
}

