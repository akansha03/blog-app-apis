package com.codewithakansha.blog.blogappapis.repositories;

import com.codewithakansha.blog.blogappapis.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
