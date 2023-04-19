package com.example.demo.mapper;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    public Product productDTO2Entity(ProductDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setAmount(productDTO.getAmount());
        product.setPrice(productDTO.getPrice());
        return product;
    }

    public ProductDTO productEntity2DTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setAmount(product.getAmount());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }

    public List<ProductDTO> ProductEntityList2DTO(List<Product> products){
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(Product product : products){
            productDTOS.add(this.productEntity2DTO(product));
        }
        return  productDTOS;
    }
}
