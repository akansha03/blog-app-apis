package com.codewithakansha.blog.blogappapis.services;

import com.codewithakansha.blog.blogappapis.payloads.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Integer postId);
    void deleteComment(Integer commentId);
}
