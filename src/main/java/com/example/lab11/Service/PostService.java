package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.Category;
import com.example.lab11.Model.Post;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.CategoryRepository;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository UserRepository;
    private final CategoryRepository categoryRepository;

    //Get All
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    //Add
    public Post addPost(Post post) {
        User user = UserRepository.findUserByUserId(post.getUserId());
        Category category = categoryRepository.findCategoryByCategoryId(post.getCategoryId());
        if (user == null) {
            throw new ApiException("User does not exist");
        } else if (category == null) {
            throw new ApiException("Category does not exist");
        }
        post.setPublishDate(LocalDate.now());
        return postRepository.save(post);
    }

    //Update
    public void updatePost(Integer postId, Post post) {
        Post p = postRepository.findPostByPostId(postId);
        if (p == null) {
            throw new ApiException("Post not found");
        }
        p.setCategoryId(post.getCategoryId());
        p.setTitle(post.getTitle());
        p.setContent(post.getContent());
        p.setUserId(post.getUserId());
        p.setPublishDate(post.getPublishDate());
        postRepository.save(p);
    }

    //Delete
    public void deletePost(Integer postId) {
        Post p = postRepository.findPostByPostId(postId);
        if (p == null) {
            throw new ApiException("Post not found");
        }
        postRepository.delete(p);
    }

    // Post by User Id
    public List<Post> getPostsByUserId(int userId) {
        User user = UserRepository.findUserByUserId(userId);
        if (user == null) {
            throw new ApiException("User does not exist");
        }
        return postRepository.findPostByUserId(userId);
    }

    //PostByTitle
    public List<Post> getPostByTitle(String title) {
        return postRepository.findPostByTitle(title);
    }

    //All Post before date by date
    public List<Post> getAllPostBeforeDate(LocalDate publishDate) {
        return postRepository.findPostByPublishDateBefore(publishDate);
    }

    //Count Posts By User ID
    public int countPostByUserId(int userId) {
        User user = UserRepository.findUserByUserId(userId);
        if (user == null) {
            throw new ApiException("User does not exist");
        }
        return postRepository.countPostsByUserId(userId);
    }
}
