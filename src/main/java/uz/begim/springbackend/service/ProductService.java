package uz.begim.springbackend.service;

import org.springframework.http.ResponseEntity;
import uz.begim.springbackend.dto.ApiResponse;
import uz.begim.springbackend.dto.ProductDto;
import uz.begim.springbackend.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ResponseEntity<Product> getProductById(Long id);

    ResponseEntity<List<Product>> getProductsByCategoryId(Long id);

    ResponseEntity<ApiResponse> save(ProductDto productDto);

    ResponseEntity<ApiResponse> updateProduct(ProductDto productDto);

    ResponseEntity<List<Product>> getByPagination(Optional<Integer> size, Optional<Integer> page);

    ResponseEntity<Integer> getCount();
}
