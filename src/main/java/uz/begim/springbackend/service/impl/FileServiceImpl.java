package uz.begim.springbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.begim.springbackend.exceptions.NotFoundException;
import uz.begim.springbackend.model.Product;
import uz.begim.springbackend.repository.ProductRepository;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileServiceImpl {
    private final ProductRepository productRepository;

    public ResponseEntity<String> uploadFile(MultipartFile multipartFile, Long productId) {
        try {
            String FILE_PATH = "/Files-Upload/";
            multipartFile.transferTo(new File(FILE_PATH + multipartFile.getName()));
            Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
            product.setImage(multipartFile.getName());
            productRepository.save(product);
        } catch (IOException e) {
            return ResponseEntity.ok("File not uploaded");
        }
        return ResponseEntity.ok("File uploaded");
    }

}
