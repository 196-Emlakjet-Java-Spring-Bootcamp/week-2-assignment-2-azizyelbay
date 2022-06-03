package com.example.week2.repository;

import com.example.week2.model.Category;
import com.example.week2.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findPostsByAuthor(String author);
    List<Post> findPostsByCategory(Category category);

}
