package com.otus.finalexam.newsmanager.post.repository

import com.otus.finalexam.newsmanager.post.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository extends JpaRepository<Post, Long> {

}