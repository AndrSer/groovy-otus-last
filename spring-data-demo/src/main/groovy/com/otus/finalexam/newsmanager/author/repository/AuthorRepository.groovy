package com.otus.finalexam.newsmanager.author.repository

import com.otus.finalexam.newsmanager.author.Author
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByFullName(String fullName)

}