package com.example.lab11.Controller;

import com.example.lab11.Model.Post;
import com.example.lab11.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //Get
    @GetMapping("/get")
    public ResponseEntity getAllPost(){
        return ResponseEntity.status(200).body(postService.getAllPosts());
    }

    //Add
    @PostMapping("/add")
    public ResponseEntity addPost(@Valid @RequestBody Post post){
        postService.addPost(post);
        return ResponseEntity.status(201).body("Successfully added post");
    }

    //Update
    @PutMapping("/update/{postId}")
    public ResponseEntity updatePost(@PathVariable Integer postId , @Valid @RequestBody Post post, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        postService.updatePost(postId,post);
        return ResponseEntity.status(201).body("Post updated successfully");
    }

    //Delete
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return ResponseEntity.status(201).body("Post deleted successfully");
    }

    //Post By User Id
    @GetMapping("/{userId}")
    public ResponseEntity getPostByUserId(@PathVariable int userId){
        return ResponseEntity.status(200).body(postService.getPostsByUserId(userId));
    }

    //Post by title
    @GetMapping("/title/{title}")
    public ResponseEntity getPostByTitle(@PathVariable String title){
        return ResponseEntity.status(200).body(postService.getPostByTitle(title));
    }

    //Before publish date
    @GetMapping("/before-date/{publishDate}")
    public ResponseEntity getPostByPublishDate(@PathVariable LocalDate publishDate){
        return ResponseEntity.status(200).body(postService.getAllPostBeforeDate(publishDate));
    }

    //Count posts By user id
    @GetMapping("/post-count/{userId}")
    public ResponseEntity getPostCountByUserId(@PathVariable int userId){
        return ResponseEntity.status(200).body("Count of Post is : "+postService.countPostByUserId(userId));
    }
}
