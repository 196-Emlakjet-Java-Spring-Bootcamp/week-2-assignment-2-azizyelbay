package com.example.week2.controller;

import com.example.week2.model.Category;
import com.example.week2.model.Post;
import com.example.week2.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/getall")
    public List<Post> getPosts(){

        List<Post> posts = postService.findAll();
        return posts;
    }

    @PostMapping("/save-post")
    public ResponseEntity<String> savePost(@RequestBody Post post){

        Post post1 = post;
        postService.createPost(post1);

        return ResponseEntity.ok("Successful");
    }

    @GetMapping("/get-id")
    public Optional<Post> getPost(@RequestParam("id") Long id) {
        Optional<Post> post = this.postService.getPost(id);
        return post;
    }

    @GetMapping("/get-author")
    public List<Post> findPostsByAuthor(@RequestParam("author") String author) {
        List<Post> posts = this.postService.findPostsByAuthor(author);
        return posts;
    }

    @GetMapping("/get-category")
    public List<Post> findPostsByCategory(@RequestParam("category") Category category) {
        List<Post> posts = this.postService.findPostsByCategory(category);
        return posts;
    }

    @GetMapping("/get-latest")
    public List<Post> getLatestThreePost() {
        List<Post> lastThreePosts = this.postService.getLatestThreePost();
        return lastThreePosts;
    }

    @DeleteMapping("/delete-id")
    public void deletePost(@RequestParam("id") Long id) {
        postService.deleteById(id);
    }

    @PutMapping("/update-post")
    public ResponseEntity<String> updatePost(@RequestBody Post post, @RequestParam Long id, String user){
        postService.updatePost(post,id,user);

        return ResponseEntity.ok("Successful");
    }


}
