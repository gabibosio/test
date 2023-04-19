package com.example.demo.service.implement;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.productDTO2Entity(productDTO);
        Product productSave = productRepository.save(product);
        return productMapper.productEntity2DTO(productSave);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, String id) {
        Product product = productRepository.findById(id).orElse(null);
        assert product != null;
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setAmount(productDTO.getAmount());
        product.setPrice(productDTO.getPrice());
        Product productSave = productRepository.save(product);
        return productMapper.productEntity2DTO(productSave);
    }

    @Override
    public ProductDTO getProductById(String id) {
        Product product = productRepository.findById(id).orElse(null);
        assert product != null;
        return productMapper.productEntity2DTO(product);
    }

    @Override
    public void deleteProduct(String id) throws ChangeSetPersister.NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.deleteById(id);
        }else {
             throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<ProductDTO> getProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.ProductEntityList2DTO(products);
    }
}
