package com.otus.finalexam.newsmanager.author.service

import com.otus.finalexam.newsmanager.author.Author
import com.otus.finalexam.newsmanager.author.dto.AuthorDTO
import com.otus.finalexam.newsmanager.author.repository.AuthorRepository
import com.otus.finalexam.newsmanager.post.Post
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthorServiceImpl implements AuthorService {

    @Autowired
    protected AuthorRepository authorRepository

    @Override
    @Transactional
    def save(AuthorDTO author) {

        def savedAuthorType = new Author()
        savedAuthorType.with {
            firstName = author.getFirstName()
            secondName = author.getSecondName()
            fullName = author.getFullName()
            emailAddress = author.getEmailAddress()
        }

        authorRepository.save(savedAuthorType)
    }

    @Override
    @Transactional(readOnly = true)
    def listAll() {
        def listAuthors = (List<Author>) authorRepository.findAll()

        def output = []
        listAuthors.forEach {
            def authorOutputDTO = new AuthorDTO()
            authorOutputDTO.setId(it.getId())
            authorOutputDTO.setFirstName(it.getFirstName())
            authorOutputDTO.setSecondName(it.getSecondName())
            authorOutputDTO.setFullName(it.getFullName())
            authorOutputDTO.setEmailAddress(it.getEmailAddress())
            authorOutputDTO.setPostNames(getSetOfPostNames(it.getPosts()))
            output.add(authorOutputDTO)
        }
        return output
    }

    @Override
    @Transactional(readOnly = true)
    def getAuthor(Long oid) {
        def author = authorRepository.findById(oid)
        def outputAuthor = new AuthorDTO()

        if (author.isPresent()) {
            outputAuthor.with {
                id = author.get().getId()
                firstName = author.get().getFirstName()
                secondName = author.get().getSecondName()
                fullName = author.get().getFullName()
                emailAddress = author.get().getEmailAddress()
                postNames = getSetOfPostNames(author.get().getPosts())
            }
        }
        return outputAuthor
    }

    @Override
    @Transactional
    def deleteAuthor(Long id) {
        authorRepository.deleteById(id)
    }

    @Override
    @Transactional
    def update(Long id, AuthorDTO author) {
        def authorTypeObject = authorRepository.findById(id)

        if (authorTypeObject.isPresent()) {
            def authorType = authorTypeObject.get()
            authorType.setFirstName(author.getFirstName())
            authorType.setSecondName(author.getSecondName())
            authorType.setFullName(author.getFullName())
            authorType.setEmailAddress(author.getEmailAddress())

            authorRepository.save(authorType)
            author.setId(authorType.getId())
            author.setPostNames(getSetOfPostNames(authorType.getPosts()))

            return author
        }
        return new AuthorDTO()
    }

    protected static def getSetOfPostNames(Set<Post> posts) {
        return posts.collect { it.getName() }.toSet()
    }
}
