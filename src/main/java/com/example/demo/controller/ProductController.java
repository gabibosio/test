package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO){
        productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable String id,@RequestBody ProductDTO productDTO){
        try {
            ProductDTO productDTO1 = productService.updateProduct(productDTO,id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(productDTO1);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id){
        try {
            ProductDTO productDTO = productService.getProductById(id);
            return ResponseEntity.status(HttpStatus.OK).body(productDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id){
        try{
            productService.deleteProduct(id);
        } catch (ChangeSetPersister.NotFoundException e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public List<ProductDTO> getProducts(){
        List<ProductDTO> productDTOS = productService.getProducts();
        return productDTOS.stream().sorted(Comparator.comparingDouble(ProductDTO::getPrice)).collect(Collectors.toList());
    }
}
