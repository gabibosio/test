package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO updateProduct(ProductDTO productDTO,long id);

    ProductDTO getProductById(long id);

    void deleteProduct(long id) throws ChangeSetPersister.NotFoundException;

    List<ProductDTO> getProducts();
}
