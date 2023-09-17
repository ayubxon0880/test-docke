package uz.begim.springbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.begim.springbackend.dto.ApiResponse;
import uz.begim.springbackend.dto.ProductDto;
import uz.begim.springbackend.exceptions.NotFoundException;
import uz.begim.springbackend.mapper.ProductMapper;
import uz.begim.springbackend.model.Product;
import uz.begim.springbackend.repository.ProductRepository;
import uz.begim.springbackend.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ResponseEntity<Product> getProductById(Long id) {
        return ResponseEntity.ok(productRepository.findById(id).orElseThrow(() -> new NotFoundException("Maxsulot topilmadi")));
    }

    @Override
    public ResponseEntity<List<Product>> getProductsByCategoryId(Long id) {
        List<Product> products = productRepository.findByCategoryId(id);
        return ResponseEntity.ok(products);
    }

    @Override
    public ResponseEntity<ApiResponse> save(ProductDto productDto) {
        productRepository.save(productMapper.toEntity(productDto));
        return ResponseEntity.ok(ApiResponse
                .builder()
                .message("Maxsulot saqlandi")
                .status(201)
                .success(true)
                .build());
    }

    @Override
    public ResponseEntity<ApiResponse> updateProduct(ProductDto productDto) {
        productRepository.save(productMapper.toEntity(productDto));
        return ResponseEntity.ok(ApiResponse
                .builder()
                .message("Maxsulot o'zgartirildi")
                .status(201)
                .success(true)
                .build());
    }

    @Override
    public ResponseEntity<List<Product>> getByPagination(Optional<Integer> size, Optional<Integer> page) {
        PageRequest pageRequest = null;
        if (size.isPresent() && page.isPresent()) {
            pageRequest = PageRequest.of(page.get(), size.get());
        } else {
            pageRequest = PageRequest.of(0,10);
        }
        List<Product> list = productRepository.findAll(pageRequest).get().toList();
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<Integer> getCount() {
        return ResponseEntity.ok(productRepository.countAll());
    }
}
