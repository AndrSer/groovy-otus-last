package com.otus.finaletask.newsmanager.post.repository

import com.otus.finaletask.newsmanager.post.PostType
import org.springframework.data.repository.PagingAndSortingRepository

interface PostRepository extends PagingAndSortingRepository<PostType, Long> {

}