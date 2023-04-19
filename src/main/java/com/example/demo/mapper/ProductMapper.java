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
        product.setNombre(productDTO.getNombre());
        product.setDescripcion(productDTO.getDescripcion());
        product.setCantidad(productDTO.getCantidad());
        product.setPrecio(productDTO.getPrecio());
        return product;
    }

    public ProductDTO productEntity2DTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setNombre(product.getNombre());
        productDTO.setDescripcion(product.getDescripcion());
        productDTO.setCantidad(product.getCantidad());
        productDTO.setPrecio(product.getPrecio());
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
