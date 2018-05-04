package ru.task.library_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.task.library_spring.entity.User;
import ru.task.library_spring.repos.UserRepo;

@Controller
public class GreetingController {

    private final UserRepo userRepo;

    @Autowired
    public GreetingController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping()
    public String main(String name, Model model) {
        Iterable<User> users = userRepo.findAll();

        model.addAttribute("users", users);
        return "main";
    }

}