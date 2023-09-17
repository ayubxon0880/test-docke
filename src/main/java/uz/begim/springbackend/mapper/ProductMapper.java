package uz.begim.springbackend.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.begim.springbackend.dto.ProductDto;
import uz.begim.springbackend.exceptions.NotFoundException;
import uz.begim.springbackend.model.Product;
import uz.begim.springbackend.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class ProductMapper {
    private final CategoryRepository categoryRepository;

    public Product toEntity(ProductDto productDto) {
        return new Product(productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getDescription(),
                productDto.getImage(),
                categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() -> new NotFoundException("Bo'lim topilmadi")));
    }
}
