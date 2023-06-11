package com.springteam.computerstore.repository;

import com.springteam.computerstore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public Product getProductById(Long id);

//    public boolean existsBy...(...) нужен метод для проверки есть ли такой товар в базе



}
