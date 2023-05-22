package com.otus.finalexam.newsmanager.post.controller

import com.otus.finalexam.newsmanager.post.dto.PostDTO
import com.otus.finalexam.newsmanager.post.service.PostService
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
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/posts")
class PostController {

    @Autowired
    protected PostService postService

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    def getAllPosts() {
        return postService.listAll()
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    def getPost(@Validated @PathVariable Long id) {
        return postService.getPost(id)
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    def createPost(@Validated @RequestParam Long authorId, @RequestBody PostDTO inputDTO) {
        return postService.save(authorId, inputDTO)
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deletePost(@Validated @PathVariable Long id) {
        postService.deletePost(id)
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    def updatePost(@Validated @PathVariable Long id, @RequestBody PostDTO post) {
        postService.updatePost(id, post)
    }
}
