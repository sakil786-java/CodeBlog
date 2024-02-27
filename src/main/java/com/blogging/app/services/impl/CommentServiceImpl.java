package com.blogging.app.services.impl;

import com.blogging.app.enitities.Comment;
import com.blogging.app.enitities.Post;
import com.blogging.app.exceptions.ResourceNotFoundException;
import com.blogging.app.payloads.CommentsDto;
import com.blogging.app.repositories.CommentRepo;
import com.blogging.app.repositories.PostRepo;
import com.blogging.app.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentsDto createComment(CommentsDto commentsDto, Integer postId) {

        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        Comment comment = this.modelMapper.map(commentsDto, Comment.class);
        comment.setPost(post);

        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment,CommentsDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
    Comment comment=this.commentRepo.findById(commentId)
            .orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", commentId));
    this.commentRepo.delete(comment);
    }
}
