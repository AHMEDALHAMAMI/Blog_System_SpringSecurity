package org.example.blogsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.blogsystem.Api.ApiResponse;
import org.example.blogsystem.Model.Blog;
import org.example.blogsystem.Model.User;
import org.example.blogsystem.Service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping
    public ResponseEntity<ApiResponse> addBlog(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody Blog blog) {

        blogService.addBlog(user, blog);

        return ResponseEntity.status(200)
                .body(new ApiResponse("Blog added successfully"));
    }

    @GetMapping("/my")
    public ResponseEntity<List<Blog>> getMyBlogs(
            @AuthenticationPrincipal User user) {

        return ResponseEntity.status(200)
                .body(blogService.getMyBlogs(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(
            @AuthenticationPrincipal User user,
            @PathVariable Integer id) {

        return ResponseEntity.status(200)
                .body(blogService.getBlogById(user, id));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Blog>> getBlogByTitle(
            @AuthenticationPrincipal User user,
            @PathVariable String title) {

        return ResponseEntity.status(200)
                .body(blogService.getBlogByTitle(user, title));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateBlog(
            @AuthenticationPrincipal User user,
            @PathVariable Integer id,
            @Valid @RequestBody Blog blog) {

        blogService.updateBlog(user, id, blog);

        return ResponseEntity.status(200)
                .body(new ApiResponse("Blog updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBlog(
            @AuthenticationPrincipal User user,
            @PathVariable Integer id) {

        blogService.deleteBlog(user, id);

        return ResponseEntity.status(200)
                .body(new ApiResponse("Blog deleted successfully"));
    }
}