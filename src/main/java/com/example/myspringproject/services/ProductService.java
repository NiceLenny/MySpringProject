package com.example.myspringproject.services;

import com.example.myspringproject.models.Category;
import com.example.myspringproject.models.Product;
import com.example.myspringproject.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Данный метод позволяет получить список всех товаров
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    // Данный метод позволяет получить товар по id
    public Product getProductId(int id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    // Данный метод позволяет сохранить товар
    @Transactional
    public void saveProduct(Product product, Category category){
        product.setCategory(category);
        productRepository.save(product);
    }

    // Данный метод позволяет обновить данные о товаре
    @Transactional
    public void updateProduct(int id, Product product){
        product.setId(id);
        productRepository.save(product);
    }

    // Данный метод позволяет удалить товар по id
    @Transactional
    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }

//    public void findByTitleContainingIgnoreCase(Product product){
//        productRepository.findByTitleContainingIgnoreCase(product.getTitle());
//
//    }
//
//    public void findByTitleAndPriceGreaterThanEqualAndPriceLessThanEqual(Product product){
//        productRepository.findByTitleAndPriceGreaterThanEqualAndPriceLessThanEqual(product.getTitle(),product.getPrice(),product.getPrice());
//    }
//
//    public void findByTitleOrderByPriceAsc(Product product){
//        productRepository.findByTitleOrderByPriceAsc(product.getTitle(),product.getPrice(),product.getPrice());
//    }
//
//    public void findByTitleOrderByPriceDesc(Product product){
//        productRepository.findByTitleOrderByPriceDesc(product.getTitle(),product.getPrice(),product.getPrice());
//    }

//    public void findByTitleAndCategoryOrderByPriceAsc(Product product){
//        productRepository.findByTitleAndCategoryOrderByPriceAsc(product.getTitle(),product.getPrice(),product.getPrice(),product.getCategory());
//    }
//
//    public void findByTitleAndCategoryOrderByPriceDesc(Product product){
//        productRepository.findByTitleAndCategoryOrderByPriceDesc(product.getTitle(),product.getPrice(),product.getPrice(),product.getCategory());
//    }

}
