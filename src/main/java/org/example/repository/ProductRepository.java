package org.example.repository;

import org.example.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByCategory_Id(Pageable pageable, Long categoryId);

    @Query(value = "from Product order by addDate desc limit 6")
    List<Product> findAllNew();

    @Query(value = "from Product order by visitCount desc limit 6")
    List<Product> findAllPopular();
}
