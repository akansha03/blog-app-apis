package com.codewithakansha.blog.blogappapis.services;

import com.codewithakansha.blog.blogappapis.payloads.PostDto;
import com.codewithakansha.blog.blogappapis.payloads.PostResponse;

import java.util.List;

public interface PostService {

    //create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto, Integer postId);

    // delete
    void deletePost(Integer postId);

    //get all posts
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);

    //get Post
    PostDto getPostById(Integer postId);

    //get Post by category

    List<PostDto> getPostsByCategory(Integer categoryId);

    // get all posts by user
    List<PostDto> getPostsByUser(Integer userId);

    // search posts
    List<PostDto> searchPosts(String keyword);
}

