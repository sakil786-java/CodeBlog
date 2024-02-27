package com.blogging.app.services;

import com.blogging.app.enitities.Post;
import com.blogging.app.payloads.PostDto;
import com.blogging.app.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);



    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPost(Integer PageNumber, Integer pageSize,String sortBy,String sortDir);

    PostDto getPostById(Integer postId);

    //get All post by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    //get All post by categUserory
    List<PostDto> getPostsByUser(Integer userId);

    //get All post by keyword
    List<PostDto> searchPostByTitle(String title);

    List<PostDto> searchPostByKeyword(String keyword);
}
