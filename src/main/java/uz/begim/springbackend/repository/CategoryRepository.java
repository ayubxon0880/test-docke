package uz.begim.springbackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.begim.springbackend.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
