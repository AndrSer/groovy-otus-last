package com.otus.finalexam.newsmanager.author.service

import com.otus.finalexam.newsmanager.author.dto.AuthorDTO

interface AuthorService {

    def save(AuthorDTO author)
    def listAll()
    def getAuthor(Long id)
    def deleteAuthor(Long id)
    def update(Long id, AuthorDTO author)
}