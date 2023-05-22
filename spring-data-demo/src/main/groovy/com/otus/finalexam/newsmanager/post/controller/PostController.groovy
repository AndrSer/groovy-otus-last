package com.otus.finalexam.newsmanager.post.controller

import com.otus.finalexam.newsmanager.post.dto.PostDTO
import com.otus.finalexam.newsmanager.post.service.PostService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
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

    @Operation(summary = "Получает список всех постов")
    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    def getAllPosts() {
        return postService.listAll()
    }

    @Operation(summary = "Получает пост")
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    def getPost(@Validated @PathVariable @Parameter(description = "id поста в БД") Long id) {
        return postService.getPost(id)
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Создает пост")
    def createPost(@Validated @RequestParam
                       @Parameter(description = "id автора в БД, который создает пост")
                               Long authorId, @RequestBody PostDTO inputDTO) {
        return postService.save(authorId, inputDTO)
    }

    @Operation(summary = "Удаляет пост")
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deletePost(@Validated @PathVariable @Parameter(description = "id поста в БД") Long id) {
        postService.deletePost(id)
    }

    @Operation(summary = "Обновляет пост")
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    def updatePost(@Validated @PathVariable @Parameter(description = "id поста в БД") Long id, @RequestBody PostDTO post) {
        postService.updatePost(id, post)
    }

    @Operation(summary = "Лайкнуть пост. Добавить ему рейтинг на еденицу")
    @PutMapping(value = "/{id}/like", produces = MediaType.APPLICATION_JSON_VALUE)
    def likePost(@Validated @PathVariable @Parameter(description = "id поста в БД") Long id) {
        postService.like(id)
    }

    @Operation(summary = "Дизлайкнуть пост. Сбавить ему рейтинг на еденицу")
    @PutMapping(value = "/{id}/dislike", produces = MediaType.APPLICATION_JSON_VALUE)
    def dislikePost(@Validated @PathVariable Long id) {
        postService.dislike(id)
    }
}
