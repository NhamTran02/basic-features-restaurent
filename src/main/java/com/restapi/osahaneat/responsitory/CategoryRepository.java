package com.restapi.osahaneat.responsitory;

import com.restapi.osahaneat.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
