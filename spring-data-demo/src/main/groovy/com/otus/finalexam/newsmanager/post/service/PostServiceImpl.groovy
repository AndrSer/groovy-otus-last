package com.otus.finalexam.newsmanager.post.service

import com.otus.finalexam.newsmanager.author.dto.ShortAuthorDTO
import com.otus.finalexam.newsmanager.author.repository.AuthorRepository
import com.otus.finalexam.newsmanager.post.Post
import com.otus.finalexam.newsmanager.post.dto.PostDTO
import com.otus.finalexam.newsmanager.post.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository

    @Autowired
    AuthorRepository authorRepository

    @Override
    @Transactional
    def save(Long authorId, PostDTO post) {

        def savedPostType = new Post()
        savedPostType.with {
            name = post.getName()
            header = post.getHeader()
            text = post.getText()
            publishDate = LocalDateTime.parse(post.getPublishDate())

            def authorInDb = authorRepository
                    .findById(authorId)

            if (authorInDb.isPresent()) {
                author = authorInDb.get()
            }
        }
        postRepository.save(savedPostType)
    }

    @Override
    @Transactional(readOnly = true)
    def listAll() {
        def listPosts = (List<Post>) postRepository.findAll()

        def output = []
        listPosts.forEach {
            def postDTO = new PostDTO()
            postDTO.setId(it.getId())
            postDTO.setName(it.getName())
            postDTO.setHeader(it.getHeader())
            postDTO.setPublishDate(it.getPublishDate())
            postDTO.setText(it.getText())
            postDTO.setRating(it.getRating())

            def authorDTO = new ShortAuthorDTO()
            authorDTO.setId(it.getAuthor()?.getId())
            authorDTO.setFullName(it.getAuthor()?.getFullName())
            authorDTO.setEmailAddress(it.getAuthor()?.getEmailAddress())

            postDTO.setAuthor(authorDTO)
            output.add(postDTO)
        }
        return output
    }

    @Override
    @Transactional(readOnly = true)
    def getPost(Long oid) {
        def post = postRepository.findById(oid)
        def outputPost = new PostDTO()

        if (post.isPresent()) {
            outputPost.with {
                id = post.get().getId()
                name = post.get().getName()
                header = post.get().getHeader()
                text = post.get().getText()
                publishDate = post.get().getPublishDate()

                def authorDTO = new ShortAuthorDTO()
                authorDTO.setId(post.get().getAuthor()?.getId())
                authorDTO.setFullName(post.get().getAuthor()?.getFullName())
                authorDTO.setEmailAddress(post.get().getAuthor()?.getEmailAddress())
                author = authorDTO

                rating = post.get().getRating()
            }
        }
        return outputPost
    }

    @Override
    @Transactional
    def deletePost(Long id) {
        postRepository.deleteById(id)
    }

    @Override
    @Transactional
    def updatePost(Long id, PostDTO post) {
        def postTypeObject = postRepository.findById(id)

        if (postTypeObject.isPresent()) {
            def postType = postTypeObject.get()
            postType.setName(post.getName())
            postType.setHeader(post.getHeader())
            postType.setText(post.getText())
            postType.setPublishDate(LocalDateTime.parse(post.getPublishDate()))

            def savedPost = postRepository.save(postType)
            def resetDto = new PostDTO()
            resetDto.setId(savedPost.getId())
            resetDto.setName(savedPost.getName())
            resetDto.setHeader(savedPost.getHeader())
            resetDto.setText(savedPost.getText())
            resetDto.setPublishDate(savedPost.getPublishDate())

            def resetAuthor = new ShortAuthorDTO()
            resetAuthor.setId(savedPost.getAuthor()?.getId())
            resetAuthor.setFullName(savedPost.getAuthor()?.getFullName())
            resetAuthor.setEmailAddress(savedPost.getAuthor()?.getEmailAddress())

            resetDto.setAuthor(resetAuthor)
            return resetDto
        } else {
            return new PostDTO()
        }
    }
}
