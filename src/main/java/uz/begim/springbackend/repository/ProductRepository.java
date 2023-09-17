package uz.begim.springbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.begim.springbackend.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategoryId(Long id);
    @Query(value = "SELECT count(p) FROM Product p")
    Integer countAll();
}
