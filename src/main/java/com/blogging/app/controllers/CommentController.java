package com.blogging.app.controllers;

import com.blogging.app.enitities.Comment;
import com.blogging.app.payloads.ApiResponse;
import com.blogging.app.payloads.CommentsDto;
import com.blogging.app.services.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentsDto>createComment(@RequestBody CommentsDto comment,@PathVariable Integer postId)
    {
        CommentsDto createdComment = this.commentService.createComment(comment, postId);

        return  new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse>deleteComment(@PathVariable Integer commentId)
    {
        this.commentService.deleteComment( commentId);

        return  new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully", true), HttpStatus.CREATED);
    }
}
