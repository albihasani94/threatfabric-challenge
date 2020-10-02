package com.threatfabric.challenge.rest;

import com.threatfabric.challenge.repository.HelloRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {

    private final HelloRepository repository;

    public HelloController(HelloRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String sayHello() {
        return repository.findAll().get(0).getMessage();
    }

}
