package ru.task.library_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.task.library_spring.entity.Book;
import ru.task.library_spring.entity.User;
import ru.task.library_spring.repos.BookRepo;
import ru.task.library_spring.repos.UserRepo;

@Controller
public class MainController {

    private final UserRepo userRepo;
    private final BookRepo bookRepo;

    @Autowired
    public MainController(UserRepo userRepo, BookRepo bookRepo) {
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

    @GetMapping("/users/add")
    public String addUserGet() {
        return "users/add";
    }

    @PostMapping("/users/add")
    public RedirectView addUserPost(@RequestParam String username, @RequestParam String password)
    {
        User user = new User(username, password);
        userRepo.save(user);

        return new RedirectView("/users");
    }

    @GetMapping("/books/add")
    public String addBookGet() {
        return "books/add";
    }

    @PostMapping("/books/add")
    public RedirectView addBookPost(@RequestParam String isbn, @RequestParam String name, @RequestParam String author)
    {
        Book book = new Book(isbn, name, author);
        bookRepo.save(book);

        return new RedirectView("/books");
    }

    @PostMapping("/users")
    public RedirectView deleteUser(@RequestParam Long id)
    {
        User user = userRepo.findById(id).get();
        userRepo.delete(user);

        return new RedirectView("/users");
    }

    @PostMapping("/books")
    public RedirectView bookAction(@RequestParam String isbn, @RequestParam String type, @RequestParam String login)
    {
        User user = userRepo.findByLogin(login);
        Book book = bookRepo.findByIsbn(isbn);
        if (type.equals("take"))
        {
            book.setWho_take(user);
            bookRepo.save(book);
        }
        else if (type.equals("return"))
        {
            book.setWho_take(null);
            bookRepo.save(book);
        }
        else if (type.equals("delete"))
        {
            bookRepo.delete(book);
        }


        //User user = userRepo.findById(id).get();
        //userRepo.delete(user);

        return new RedirectView("/books");
    }

//    //redirect to users page
//    @GetMapping()
//    public RedirectView redirectWithUsingRedirectView() {
//        return new RedirectView("/users");
//    }

}