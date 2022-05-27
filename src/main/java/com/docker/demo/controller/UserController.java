package com.docker.demo.controller;

import com.docker.demo.model.User;
import com.docker.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/add")
    @Caching(evict = {
            @CacheEvict(value = "users", allEntries = true)})
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @GetMapping("/all")
    @Cacheable(value = "users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
