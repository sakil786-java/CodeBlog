package com.blogging.app.repositories;

import com.blogging.app.enitities.Category;
import com.blogging.app.enitities.Post;
import com.blogging.app.enitities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {


    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post>findByTitleContaining(String title);

    @Query("select p from Post p where p.content like :key")
    List<Post>searchByKeyword(@Param("key") String keyword);

}
