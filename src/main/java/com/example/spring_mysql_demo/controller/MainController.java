package com.example.spring_mysql_demo.controller;

import com.example.spring_mysql_demo.repositories.UserRepository;
import com.example.spring_mysql_demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/demo")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @PutMapping(path = "/update/{id}")
    public @ResponseBody String updateUser(@PathVariable Integer id, @RequestParam String name, @RequestParam String email) {


        var optionalUser = userRepository.findById(id);

        if (!optionalUser.isEmpty())
        {
            return "NOT FOUND";
        }
        var user = optionalUser.get();

        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);

        return "update";

    }


}
