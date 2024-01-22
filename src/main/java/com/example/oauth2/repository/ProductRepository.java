package com.example.oauth2.repository;

import com.example.oauth2.entity.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
//    public static final String HASH_KEY="Product";
//    private final RedisTemplate template;
//
//    public ProductRepository(RedisTemplate template) {
//        this.template = template;
//    }
//
//
//    public Product save(Product product){
//        template.opsForHash().put(HASH_KEY,product.getId(),product);
//        return product;
//    }
//
//    public List<Product> findAll(){
//        return template.opsForHash().values(HASH_KEY);
//    }
//    public Product findById(int id){
//        return (Product) template.opsForHash().get(HASH_KEY,id);
//    }
//    public String deleteProduct(int id){
//       template.opsForHash().delete(HASH_KEY,id);
//       return "product removed !";
//    }
}
