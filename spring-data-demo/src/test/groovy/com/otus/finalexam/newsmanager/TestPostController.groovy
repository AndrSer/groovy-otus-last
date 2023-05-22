package com.otus.finalexam.newsmanager

import com.fasterxml.jackson.databind.ObjectMapper
import com.otus.finalexam.newsmanager.author.Author
import com.otus.finalexam.newsmanager.author.repository.AuthorRepository
import com.otus.finalexam.newsmanager.post.Post
import com.otus.finalexam.newsmanager.post.repository.PostRepository
import jakarta.servlet.ServletException
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc

import java.time.LocalDateTime

import static org.junit.Assert.*
import static org.hamcrest.Matchers.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestPostController {

    @Autowired
    protected AuthorRepository authorRepository

    @Autowired
    protected PostRepository postRepository

    @Autowired
    protected ObjectMapper objectMapper

    @Autowired
    protected MockMvc mockMvc

    private Author author1

    private Post post1

    private Post post2

    private Post post3

    @BeforeAll
    void init() {
        author1 = createAuthorInRepo("test", "author1",
                "test author1", "")
        post1 = createPostInRepo("post1", "header1",
                "text", LocalDateTime.parse("2023-05-20T01:01:35"), author1)
        post2 = createPostInRepo("post2", "header2",
                "text post2", LocalDateTime.parse("2023-05-20T01:01:35")
                .minusHours(5))
        post3 = createPostInRepo("post3", "header3",
                "text post3", LocalDateTime.parse("2023-05-20T01:01:35")
                .minusHours(7))
    }

    @Order(1)
    @Test
    void '001_should_return_200_and_all_posts'() {
        def response = mockMvc.perform(get("/posts/get-all")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString()

        assertEquals(equalsJson[0], response)
    }

    @Order(2)
    @Test
    void '002_should_return_200_and_get_one_post'() {
        mockMvc.perform(get("/posts/get/${post1.getId()}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect {
                    jsonPath('$.name', equalTo(post1.getName()))
                    jsonPath('$.header', equalTo(post1.getHeader()))
                    jsonPath('$.text', equalTo(post1.getText()))
                    jsonPath('$.publishDate',
                            equalTo(post1.getPublishDate()))
                    jsonPath('$.author.fullName',
                            equalTo(post1.getAuthor().getFullName()))
                    jsonPath('$.author.emailAddress',
                            equalTo(post1.getAuthor().getEmailAddress()))
                }
    }

    @Order(3)
    @Test
    void '003_should_return_200_create_post'() {
        def response = mockMvc.perform(post("/posts/create")
                .param("authorId", "6")
                .contentType(MediaType.APPLICATION_JSON)
                .content( """{
                                       "name" : "newPost1",
                                       "header" : "header new post",
                                       "text" : "text of new post",
                                       "publishDate" : "${LocalDateTime
                        .parse("2023-05-19T01:01:35")}" 
                                     }""")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect {
                    status().isOk()
                    content().contentType(MediaType.APPLICATION_JSON)
                    jsonPath('$.name', equalTo("newPost1"))
                    jsonPath('$.header', equalTo("header new post"))
                    jsonPath('$.text', equalTo("text of new post"))
                    jsonPath('$.publishDate', equalTo("2023-05-19T01:01:35"))
                    jsonPath('$.author.id', equalTo("6"))
                    jsonPath('$.author.fullName', equalTo("test author1"))
                    jsonPath('$.author.emailAddress', equalTo(""))
                }.andDo(print())
                .andReturn()

        def parseResponse = objectMapper.
                readValue(response.getResponse().getContentAsString(), Map.class)

        def createdPost = postRepository.findById((Long) parseResponse["id"])
        assertEquals(createdPost.get().getName(), "newPost1")
        assertEquals(createdPost.get().getHeader(), "header new post")
        assertEquals(createdPost.get().getText(), "text of new post")
        assertEquals(createdPost.get().getPublishDate(), LocalDateTime.parse("2023-05-19T01:01:35"))
        assertEquals(createdPost.get().getAuthor().getId(), 6)
        assertEquals(createdPost.get().getAuthor().getFirstName(), "test")
        assertEquals(createdPost.get().getAuthor().getSecondName(), "author1")
        assertEquals(createdPost.get().getAuthor().getFullName(), "test author1")
        assertEquals(createdPost.get().getAuthor().getEmailAddress(), "")
        assertEquals(createdPost.get().getAuthor().getPosts().size(), 2)
        assertEquals(createdPost.get().getAuthor().getPosts()[1].getName(), "newPost1")
    }

    @Order(4)
    @Test
    void '004_should_don\'t_create_post_with_null_or_empty_fields'() {
        assertThrows(ServletException.class,
                { mockMvc.perform(post("/posts/create")
                        .param("authorId", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( """{
                                              "name" : "",
                                              "header" : "",
                                              "text" : null,
                                              "publishDate" : "2023-05-19T01:01:35" 
                                     }""")
                        .accept(MediaType.APPLICATION_JSON))})
    }

    @Order(5)
    @Test
    void '005_should_don\'t_create_post_with_wrong_publish_date'() {
        mockMvc.perform(post("/posts/create")
                .param("authorId", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content( """{
                                              "name" : "post1",
                                              "header" : "header1",
                                              "text" : "text post",
                                              "publishDate" : "wrong date" 
                                     }""")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
    }

    @Order(6)
    @Test
    void '006_should_return_200_and_update_post'() {
        def response = mockMvc
                .perform(put("/posts/update/${post1.getId()}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( """{
                                       "name" : "post301",
                                       "header" : "${post1.getHeader()}",
                                       "text" : "${post1.getText()}",
                                       "publishDate" : "${post1.getPublishDate()}" 
                                     }""")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect {
                    status().isOk()
                    content().contentType(MediaType.APPLICATION_JSON)
                    jsonPath('$.name', "post301")
                    jsonPath('$.header', post1.getHeader())
                    jsonPath('$.text', post1.getText())
                    jsonPath('$.publishDate', post1.getPublishDate())
                    jsonPath('$.author.id', equalTo("6"))
                    jsonPath('$.author.fullName', equalTo("test author1"))
                    jsonPath('$.author.emailAddress', equalTo(""))
                }.andDo(print()).andReturn()

        def parseResponse = objectMapper.
                readValue(response.getResponse().getContentAsString(), Map.class)

        def updatedPost = postRepository.findById((Long) parseResponse["id"])
        assertEquals(updatedPost.get().getName(), "post301")
        assertEquals(updatedPost.get().getHeader(), post1.getHeader())
        assertEquals(updatedPost.get().getText(), post1.getText())
        assertEquals(updatedPost.get().getPublishDate(), post1.getPublishDate())
        assertEquals(updatedPost.get().getAuthor().getId(), 6)
        assertEquals(updatedPost.get().getAuthor().getFirstName(), "test")
        assertEquals(updatedPost.get().getAuthor().getSecondName(), "author1")
        assertEquals(updatedPost.get().getAuthor().getFullName(), "test author1")
        assertEquals(updatedPost.get().getAuthor().getEmailAddress(), "")
        assertEquals(updatedPost.get().getAuthor().getPosts().size(), 2)
        assertEquals(updatedPost.get().getAuthor().getPosts()[0].getName(), "post301")
    }

    @Order(7)
    @Test
    void '007_should_don\'t_update_post_with_null_or_empty_fields'() {
        assertThrows(ServletException.class,
                { mockMvc.perform(put("/posts/update/${post1.getId()}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( """{
                                              "name" : "",
                                              "header" : "",
                                              "text" : null,
                                              "publishDate" : "2023-05-19T01:01:35"
                                     }""")
                        .accept(MediaType.APPLICATION_JSON))})
    }

    @Order(8)
    @Test
    void '008_should_return_200_and_delete_post'() {
        mockMvc.perform(delete("/posts/delete/${post1.getId()}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())

        def deletedPost = authorRepository.findById(post1.getId())
        assertTrue(deletedPost.isEmpty())
    }

    @Order(9)
    @Test
    void '009_should_return_200_and_like_post'() {
        mockMvc.perform(put("/posts/${post1.getId()}/like")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())

        assertTrue(postRepository.findById(post1.getId()).get().getRating() == 1)
    }

    @Order(10)
    @Test
    void '010_should_return_200_and_dislike_post'() {
        def response = mockMvc.perform(put("/posts/${post1.getId()}/dislike")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()

        assertTrue(postRepository.findById(post1.getId()).get().getRating() == 0)
    }


    private createAuthorInRepo(firstName, secondName,
                               fullName, emailAddress) {
        def author = new Author()
        author.with {
            it.firstName = firstName
            it.secondName = secondName
            it.fullName = fullName
            it.emailAddress = emailAddress
        }
        authorRepository.save(author)
    }

    private createPostInRepo(name, header,
                             text, publishDate) {
        def post = new Post()
        post.with {
            it.name = name
            it.header = header
            it.text = text
            it.publishDate = publishDate
        }
        postRepository.save(post)
    }

    private createPostInRepo(name, header,
                             text, publishDate,
                             author) {
        def post = new Post()
        post.with {
            it.name = name
            it.header = header
            it.text = text
            it.publishDate = publishDate
            it.author = author
        }
        postRepository.save(post)
    }

    @AfterAll
    void resetDb() {
        authorRepository.deleteAll()
        postRepository.deleteAll()
    }

    private final def equalsJson = [
            """[{"id":1,"name":"post1","header":"header1","text":"text","publishDate":"2023-05-20T01:01:35","rating":0,"author":{"id":6,"fullName":"test author1","emailAddress":""}},{"id":2,"name":"post2","header":"header2","text":"text post2","publishDate":"2023-05-19T20:01:35","rating":0,"author":{"id":null,"fullName":null,"emailAddress":null}},{"id":3,"name":"post3","header":"header3","text":"text post3","publishDate":"2023-05-19T18:01:35","rating":0,"author":{"id":null,"fullName":null,"emailAddress":null}}]"""]
}
