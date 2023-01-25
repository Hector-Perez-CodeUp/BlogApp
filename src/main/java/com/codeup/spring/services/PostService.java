package com.codeup.spring.services;
// Imports
import com.codeup.spring.models.Post;
import com.codeup.spring.repositories.PostRepository;
import com.codeup.spring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PostService")
public class PostService {
    // Fields
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    // Constructor
    public PostService(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    // Get All Posts
    public List<Post> getAllPosts() {
        return postDao.findAll();
    }
}
