package com.otus.finalexam.newsmanager.post.service

import com.otus.finalexam.newsmanager.post.dto.PostDTO

interface PostService {

    def save(Long authorId, PostDTO post)
    def listAll()
    def getPost(Long id)
    def deletePost(Long id)
    def updatePost(Long id, PostDTO post)

}