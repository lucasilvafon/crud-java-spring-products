package com.example.crud.controllers;

import com.example.crud.domain.product.ProductEntity;
import com.example.crud.domain.product.ProductRepository;
import com.example.crud.domain.product.RequestProductDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    //Faz a injeção da instancia
    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity getAllProducts(){
        var allProducts = repository.findAllByActiveTrue();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProductDTO data){

        ProductEntity newProduct = new ProductEntity(data);
        repository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProductDTO data){
        Optional<ProductEntity> optionalProduct = repository.findById(data.id());
        if (optionalProduct.isPresent()){
            ProductEntity product = optionalProduct.get();
            product.setName(data.name());
            product.setPrice_in_cents(data.price_in_cents());
            return ResponseEntity.ok(product);
        }else {
            return ResponseEntity.notFound().build();

        }
    }

    //Usando path variable
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id){
        Optional<ProductEntity> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()){
            ProductEntity product = optionalProduct.get();
            product.setActive(false);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();

        }
    }

}
