package com.example.week2.service;

import com.example.week2.model.Category;
import com.example.week2.model.Post;
import com.example.week2.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post){
        post = postRepository.save(post);
        return post;
    }

    public List<Post> findAll() {
        List<Post> posts = this.postRepository.findAll();
        return posts;
    }
    //updatePost(postbilgileri, username)
    //deletePost


    public Optional<Post> getPost(Long id) {
        Optional<Post> post = this.postRepository.findById(id);
        return post;
    }

    public List<Post> findPostsByAuthor(String author) {
        List<Post> posts = this.postRepository.findPostsByAuthor(author);
        return posts;
    }

    public List<Post> findPostsByCategory(Category category) {
        List<Post> posts = this.postRepository.findPostsByCategory(category);
        return posts;
    }

    public List<Post> getLatestThreePost() {
        List<Post> lastThreePosts = this.postRepository.findAll().subList(this.postRepository.findAll().size()-3,this.postRepository.findAll().size());
        return lastThreePosts;
    }

    public void deleteById(long id) {
        Post post = postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        postRepository.delete(post);
    }


    public Post updatePost(Post post,Long id, String user){
        Post post1 = postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        post1.setAuthor(post.getAuthor());
        post1.setCategory(post.getCategory());
        post1.setUpdatedDate(LocalDateTime.now());
        post1.setUpdatedBy(user);
        post1.setText(post.getText());
        post1.setTitle(post.getTitle());
        return postRepository.save(post1);
    }
}
