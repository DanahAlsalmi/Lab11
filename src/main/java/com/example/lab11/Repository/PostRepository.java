package com.example.lab11.Repository;

import com.example.lab11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select p from Post p where p.postId=?1")
    Post findPostByPostId(Integer postId);

    List<Post> findPostByUserId(int userId);

    @Query("select p from Post p where p.title=?1")
    List<Post> findPostByTitle(String title);

    List<Post> findPostByPublishDateBefore(LocalDate publishDate);


    @Query("select count (p) FROM Post p WHERE p.userId = :userId")
    int countPostsByUserId( int userId);
}
