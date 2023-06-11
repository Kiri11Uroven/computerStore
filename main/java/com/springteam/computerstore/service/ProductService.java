package com.springteam.computerstore.service;

import com.springteam.computerstore.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductService {
   Product addProduct();
   Product findById();
   Product findAll();
   Product updateById();
   boolean exist();//есть ли уже такой товар в базе?
}
