package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.Comment;
import com.example.lab11.Model.Post;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.CommentRepository;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository UserRepository;
    private final PostRepository postRepository;

    //Get All
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    //Add
    public Comment addComment(Comment comment) {
        User user = UserRepository.findUserByUserId(comment.getUserId());
        Post post = postRepository.findPostByPostId(comment.getPostId());
        if (user == null) {
            throw new ApiException("User Not Found");
        } else if (post == null) {
            throw new ApiException("Post Not Found");
        }
        comment.setCommentDate(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    //Update
    public void updateComment(Integer commentId, Comment comment) {
        Comment c = commentRepository.findCommentById(commentId);
        if (c == null) {
            throw new ApiException("Comment not found");
        }
        c.setUserId(comment.getUserId());
        c.setPostId(comment.getPostId());
        c.setContent(comment.getContent());
        c.setCommentDate(LocalDateTime.now());
        commentRepository.save(c);
    }

    //Delete
    public void deleteComment(Integer commentId) {
        Comment c = commentRepository.findCommentById(commentId);
        if (c == null) {
            throw new ApiException("Comment not found");
        }
        commentRepository.delete(c);
    }

    //All comment By Post Id
    public List<Comment> findCommentByPostId(int postId) {
        Post post = postRepository.findPostByPostId(postId);
        if (post == null) {
            throw new ApiException("Post Not Found");
        }
        return commentRepository.findCommentByPostId(postId);
    }

    //find all comments by User Id
    public List<Comment> findCommentByUserId(int userId) {
        User user = UserRepository.findUserByUserId(userId);
        if (user == null) {
            throw new ApiException("User Not Found");
        }
        return commentRepository.findCommentsByUserId(userId);
    }


}
