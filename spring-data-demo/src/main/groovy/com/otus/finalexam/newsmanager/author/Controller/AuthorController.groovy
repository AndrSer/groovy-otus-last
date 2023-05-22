package com.otus.finalexam.newsmanager.author.Controller

import com.otus.finalexam.newsmanager.author.dto.AuthorDTO
import com.otus.finalexam.newsmanager.author.service.AuthorService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
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
@Tag(name='Контроллер "Автор"' , description='Описывает REST для работы с сущностью "Автор"')
class AuthorController {

    @Autowired
    protected AuthorService authorService

    @Operation(summary = "Получить список всех авторов постов")
    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    def getAllAuthors() {
        return authorService.listAll()
    }

    @Operation(summary = "Получить одного автора поста")
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    def getAuthor(@Validated @PathVariable @Parameter(description = "id автора в БД") Long id) {
        return authorService.getAuthor(id)
    }

    @Operation(summary = "Создать автора поста")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    def createAuthor(@RequestBody AuthorDTO inputDTO) {
        return authorService.save(inputDTO)
    }

    @Operation(summary = "Удалить автора поста")
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteAuthor(@Validated @PathVariable @Parameter(description = "id автора в БД") Long id) {
        authorService.deleteAuthor(id)
    }

    @Operation(summary = "Обновить автора поста")
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    def updateAuthor(@Validated @PathVariable @Parameter(description = "id автора в БД") Long id,
                     @RequestBody AuthorDTO author) {
        authorService.update(id, author)
    }
}