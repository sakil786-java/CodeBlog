package com.blogging.app.controllers;

import com.blogging.app.config.AppConstant;
import com.blogging.app.enitities.Post;
import com.blogging.app.payloads.ApiResponse;
import com.blogging.app.payloads.PostDto;
import com.blogging.app.payloads.PostResponse;
import com.blogging.app.services.FileService;
import com.blogging.app.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {


    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {
        PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);

        return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
    }

    //get all posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts
    (@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
     @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
     @RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
     @RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir
    ) {
        PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto postById = this.postService.getPostById(postId);

        return new ResponseEntity<PostDto>(postById, HttpStatus.OK);

    }

    //get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
        List<PostDto> postsByUserList = this.postService.getPostsByUser(userId);

        return new ResponseEntity<List<PostDto>>(postsByUserList, HttpStatus.OK);

    }

    //get by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
        List<PostDto> postsByCategoryList = this.postService.getPostsByCategory(categoryId);

        return new ResponseEntity<List<PostDto>>(postsByCategoryList, HttpStatus.OK);

    }

    //update post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto updatedPostDto = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatedPostDto, HttpStatus.OK);
    }

    //delete post
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ApiResponse("Post Deleted Successfully", true);
    }


    @GetMapping("/posts/search/{title}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String title) {
        List<PostDto> postsByTitle = this.postService.searchPostByTitle(title);

        return new ResponseEntity<List<PostDto>>(postsByTitle, HttpStatus.OK);

    }

    @GetMapping("/posts/search/keyword/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByKeyword(@PathVariable String keyword) {
        List<PostDto> postsByKeyWords = this.postService.searchPostByKeyword(keyword);

        return new ResponseEntity<List<PostDto>>(postsByKeyWords, HttpStatus.OK);

    }

    @Value("${project.image}")
    private String path;

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId) throws IOException {

        PostDto postDto = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);
        postDto.setImageName(fileName);
        PostDto updatePost = this.postService.updatePost(postDto, postId);

        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

    }

    @GetMapping(value = "post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable ("imageName")String imageName,
            HttpServletResponse response
    ) throws IOException {
        InputStream resource=this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
