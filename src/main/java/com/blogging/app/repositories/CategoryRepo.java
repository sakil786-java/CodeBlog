package com.blogging.app.repositories;

import com.blogging.app.enitities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
