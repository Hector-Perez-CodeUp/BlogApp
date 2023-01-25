package com.codeup.spring.controller;
//Imports
import com.codeup.spring.models.Post;
import com.codeup.spring.models.User;
import com.codeup.spring.repositories.PostRepository;
import com.codeup.spring.repositories.UserRepository;
import com.codeup.spring.services.EmailService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@NoArgsConstructor
public class PostController {
    // Fields
    @Autowired
    private PostRepository postDao;
    @Autowired
    private UserRepository userDao;
    @Autowired
    private EmailService emailService;

    // All Args Constructor
    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    // Shows Index Page
    @GetMapping("/posts")
    public String show(Model model) {
        model.addAttribute("posts", postDao.findAll());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "/posts/index";
    }

    // Sends to Individual Post
    @PostMapping("/posts")
    public String create(@ModelAttribute Post post, @RequestParam(name="button") long id) {
        return "redirect:/show/" + id;
    }

    // Shows Individual Post
    @GetMapping("/show/{id}")
    public String showById(@PathVariable long id, Model model) {
        Post post = postDao.getReferenceById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    // View New Post Form
    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    // Create New Post
    @PostMapping("/create")
    public String createPost(@ModelAttribute Post post) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        emailService.prepareAndSend(post, post.getTitle(), post.getBody());
        postDao.save(post);
        return "redirect:/posts";
    }


    // Roll Dice Exercise
    // Roll Dice Main
    @RequestMapping(path = "/roll-dice", method = RequestMethod.GET)
    public String rollDice() {
        return "roll-dice";
    }

    // Check If Guess Is Correct
    @GetMapping(path = "/roll-dice/{guess}")
        public String showGuess(@PathVariable int userGuess, Model model) {
            int randInt = (int) (Math.random() * 6) + 1;
            String returnMessage;
            boolean checkInput = userGuess == randInt;
            if (checkInput) {
                returnMessage = "Your guess is correct!";
            } else {
                returnMessage = "You are incorrect.";
            }
            model.addAttribute("random", randInt);
            model.addAttribute("guess", userGuess);
            model.addAttribute("checkInput", returnMessage);

            return "roll-dice-n";
        }
}
