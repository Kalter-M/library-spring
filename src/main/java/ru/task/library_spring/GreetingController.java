package ru.task.library_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.task.library_spring.entity.Book;
import ru.task.library_spring.entity.User;
import ru.task.library_spring.repos.BookRepo;
import ru.task.library_spring.repos.UserRepo;

@Controller
public class GreetingController {

    private final UserRepo userRepo;
    private final BookRepo bookRepo;

    @Autowired
    public GreetingController(UserRepo userRepo, BookRepo bookRepo) {
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
    }

    @GetMapping("/books")
    public String books(Model model) {
        Iterable<Book> books = bookRepo.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/users")
    public String users(Model model) {
        Iterable<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    //redirect to users page
    @GetMapping()
    public RedirectView redirectWithUsingRedirectView() {
        return new RedirectView("/users");
    }

}