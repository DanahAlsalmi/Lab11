package com.example.lab11.Controller;

import com.example.lab11.Model.Comment;
import com.example.lab11.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //Get
    @GetMapping("/get")
    public ResponseEntity getAllComment(){
        return ResponseEntity.status(200).body(commentService.findAll());
    }

    //Add
    @PostMapping("/add")
    public ResponseEntity addComment(@Valid @RequestBody Comment comment, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.addComment(comment);
        return ResponseEntity.status(201).body("Comment added");
    }

    //Update
    @PutMapping("/update/{commentId}")
    public ResponseEntity updateComment(@PathVariable Integer commentId, @Valid @RequestBody Comment comment, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.updateComment(commentId,comment);
        return ResponseEntity.status(201).body("Comment updated");
    }

    //Delete
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Integer commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.status(201).body("Comment deleted");
    }

    //All comment By Post Id
    @GetMapping("/post-id/{postId}")
    public ResponseEntity allCommentByPostId(@PathVariable int postId){
        return ResponseEntity.status(200).body(commentService.findCommentByPostId(postId));
    }

    //All comment By User Id
    @GetMapping("/user-id/{userId}")
    public ResponseEntity allCommentByUserId(@PathVariable int userId){
        return ResponseEntity.status(200).body(commentService.findCommentByUserId(userId));
    }
}
