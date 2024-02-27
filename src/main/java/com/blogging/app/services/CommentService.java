package com.blogging.app.services;

import com.blogging.app.payloads.CommentsDto;

public interface CommentService {

    CommentsDto createComment(CommentsDto commentsDto,Integer postId);
    void deleteComment(Integer commentId);
}
