package org.example.blogsystem.Repository;

import org.example.blogsystem.Model.Blog;
import org.example.blogsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

    List<Blog> findAllByUser(User user);

    Blog findBlogByIdAndUser(Integer id, User user);

    List<Blog> findAllByUserAndTitleContainingIgnoreCase(User user, String title);
}