package com.otus.finaletask.newsmanager.author.Controller

import com.otus.finaletask.newsmanager.author.service.AuthorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/authors", produces = "application/json;charset=UTF-8")
class AuthorController {

    @Autowired
    AuthorService authorService

    @GetMapping(value = "/get-authors")
    def getAuthors() {


    }







}
