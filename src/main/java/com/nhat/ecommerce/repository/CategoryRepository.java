package com.nhat.ecommerce.repository;

import com.nhat.ecommerce.model.Category;
import org.aspectj.apache.bcel.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    public Category findByName(String name);

    @Query("Select c from Category c WHERE c.name=:name and c.parentCategory.name=:parentCategoryName")
    public Category findByNameAndParent(@Param("name") String name,
                                        @Param("parentCategoryName") String parentCategoryName);

}
