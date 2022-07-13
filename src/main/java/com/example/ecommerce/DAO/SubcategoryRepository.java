package com.example.ecommerce.DAO;

import com.example.ecommerce.Models.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory,Long> {
}
