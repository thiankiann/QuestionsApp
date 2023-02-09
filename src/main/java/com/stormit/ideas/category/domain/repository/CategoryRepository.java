package com.stormit.ideas.category.domain.repository;

import com.stormit.ideas.category.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID > {
    Page<Category> findByNameContainingIgnoreCase(String search, Pageable pageable);
}

