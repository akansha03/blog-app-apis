package com.codewithakansha.blog.blogappapis.repositories;

import com.codewithakansha.blog.blogappapis.entities.Category;
import com.codewithakansha.blog.blogappapis.entities.Post;
import com.codewithakansha.blog.blogappapis.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    //Page<Post> findByCategory(Category category, Pageable p);
    @Query("select p from Post p where p.title like :key")
    List<Post> findByTitleContaining(@Param("key") String title);
}
