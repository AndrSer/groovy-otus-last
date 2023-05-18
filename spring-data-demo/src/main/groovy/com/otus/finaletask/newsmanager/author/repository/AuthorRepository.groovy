package com.otus.finaletask.newsmanager.author.repository


import com.otus.finaletask.newsmanager.author.AuthorType
import org.springframework.data.repository.PagingAndSortingRepository

interface AuthorRepository extends PagingAndSortingRepository<AuthorType, Long> {

}