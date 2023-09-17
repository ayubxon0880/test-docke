package uz.begim.springbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.begim.springbackend.dto.ApiResponse;
import uz.begim.springbackend.dto.ProductDto;
import uz.begim.springbackend.model.Product;
import uz.begim.springbackend.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable Long id){
        return productService.getProductsByCategoryId(id);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getByPagination(@RequestParam Optional<Integer> limit, @RequestParam Optional<Integer> page){
        return productService.getByPagination(limit,page);
    }

    @GetMapping("/getCount")
    public ResponseEntity<Integer> getCount(){
        return productService.getCount();
    }

}
