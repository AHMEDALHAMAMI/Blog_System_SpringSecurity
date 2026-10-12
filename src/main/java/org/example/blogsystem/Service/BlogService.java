package org.example.blogsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.blogsystem.Exception.ApiException;
import org.example.blogsystem.Model.Blog;
import org.example.blogsystem.Model.User;
import org.example.blogsystem.Repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    @Transactional
    public void addBlog(User user, Blog blog) {
        blog.setId(null);
        blog.setUser(user);

        blogRepository.save(blog);
    }

    @Transactional(readOnly = true)
    public List<Blog> getMyBlogs(User user) {
        return blogRepository.findAllByUser(user);
    }

    @Transactional(readOnly = true)
    public Blog getBlogById(User user, Integer id) {
        Blog blog = blogRepository.findBlogByIdAndUser(id, user);

        if (blog == null) {
            throw new ApiException("Blog not found");
        }

        return blog;
    }

    @Transactional(readOnly = true)
    public List<Blog> getBlogByTitle(User user, String title) {
        if (title == null || title.isBlank()) {
            throw new ApiException("Title is required");
        }

        return blogRepository
                .findAllByUserAndTitleContainingIgnoreCase(user, title);
    }

    @Transactional
    public void updateBlog(User user, Integer id, Blog request) {
        Blog oldBlog = blogRepository.findBlogByIdAndUser(id, user);

        if (oldBlog == null) {
            throw new ApiException("Blog not found");
        }

        oldBlog.setTitle(request.getTitle());
        oldBlog.setBody(request.getBody());

        blogRepository.save(oldBlog);
    }

    @Transactional
    public void deleteBlog(User user, Integer id) {
        Blog blog = blogRepository.findBlogByIdAndUser(id, user);

        if (blog == null) {
            throw new ApiException("Blog not found");
        }

        blogRepository.delete(blog);
    }
}