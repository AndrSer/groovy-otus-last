package com.otus.finalexam.newsmanager.author.Controller

import com.otus.finalexam.newsmanager.author.dto.AuthorDTO
import com.otus.finalexam.newsmanager.author.service.AuthorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/authors")
class AuthorController {

    @Autowired
    protected AuthorService authorService

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    def getAllAuthors() {
        return authorService.listAll()
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    def getAuthor(@Validated @PathVariable Long id) {
        return authorService.getAuthor(id)
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    def createAuthor(@RequestBody AuthorDTO inputDTO) {
        return authorService.save(inputDTO)
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteAuthor(@Validated @PathVariable Long id) {
        authorService.deleteAuthor(id)
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    def updateAuthor(@Validated @PathVariable Long id, @RequestBody AuthorDTO author) {
        authorService.update(id, author)
    }
}