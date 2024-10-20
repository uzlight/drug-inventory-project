package com.techelevator.controller;
import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/users")
@PreAuthorize("permitAll")
public class UserController {
    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }
    @RequestMapping (path = "", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userDao.getUsers();
    }

//    @RequestMapping (method = RequestMethod.GET)
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable int userId) {
        return userDao.getUserById(userId);
    }
//    @RequestMapping (method = RequestMethod.GET)
    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userDao.getUserByUsername(username);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping (path = "", method = RequestMethod.POST)
    public User createUser(@RequestBody User newUser) {
        return userDao.createUser(newUser);
    }

}
