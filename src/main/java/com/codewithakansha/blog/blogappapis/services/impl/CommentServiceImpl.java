package com.codewithakansha.blog.blogappapis.services.impl;

import com.codewithakansha.blog.blogappapis.entities.Comment;
import com.codewithakansha.blog.blogappapis.entities.Post;
import com.codewithakansha.blog.blogappapis.exceptions.ResourceNotFoundException;
import com.codewithakansha.blog.blogappapis.payloads.CommentDto;
import com.codewithakansha.blog.blogappapis.repositories.CommentRepo;
import com.codewithakansha.blog.blogappapis.repositories.PostRepo;
import com.codewithakansha.blog.blogappapis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment comment = this.commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
        this.commentRepo.delete(comment);
    }
}
