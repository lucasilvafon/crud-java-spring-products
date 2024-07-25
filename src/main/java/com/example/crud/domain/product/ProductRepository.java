package com.example.crud.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;


//Precisa receber a entidade que vai manupular e o tipo da primary key
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

}
