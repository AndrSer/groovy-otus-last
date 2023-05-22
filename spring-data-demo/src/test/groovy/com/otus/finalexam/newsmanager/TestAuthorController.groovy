package com.otus.finalexam.newsmanager

import com.fasterxml.jackson.databind.ObjectMapper
import com.otus.finalexam.newsmanager.author.Author
import com.otus.finalexam.newsmanager.author.repository.AuthorRepository
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
class TestAuthorController {

    @Autowired
    protected AuthorRepository authorRepository

    @Autowired
    protected ObjectMapper objectMapper

    @Autowired
    protected MockMvc mockMvc

    private Author author1
    private Author author2
    private Author author3

    @BeforeAll
    void init() {
        author1 = createAuthorInRepo("test", "author1",
                "test author1", "")
        author2 = createAuthorInRepo("test", "author2",
                "test author2", "test_author2@tr.org")
        author3 = createAuthorInRepo("test", "author3",
                "test author3", "test_author3@lm.org")
    }

    @Order(1)
    @Test
    void '001_should_return_200_and_all_authors'() {
        def response = mockMvc.perform(get("/authors/get-all")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString()

        assertEquals(equalsJson[0], response)
    }

    @Order(2)
    @Test
    void '002_should_return_200_and_get_one_author'() {
        mockMvc.perform(get("/authors/get/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect {
                    jsonPath('$.firstName', equalTo(author2.getFirstName()))
                    jsonPath('$.secondName', equalTo(author2.getSecondName()))
                    jsonPath('$.fullName', equalTo(author2.getFullName()))
                    jsonPath('$.emailAddress', equalTo(author2.getEmailAddress()))
                }
    }

    @Order(3)
    @Test
    void '003_should_return_200_create_author'() {
        def response = mockMvc.perform(post("/authors/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content( """{
                                       "firstName" : "Davie504",
                                       "secondName" : "Dave",
                                       "fullName" : "Davie504 Dave",
                                       "emailAddress" : "davie504@link.org" 
                                     }""")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect {
                    status().isOk()
                    content().contentType(MediaType.APPLICATION_JSON)
                    jsonPath('$.firstName', equalTo("Davie504"))
                    jsonPath('$.secondName', equalTo("Dave"))
                    jsonPath('$.fullName', equalTo("Davie504 Dave"))
                    jsonPath('$.emailAddress', equalTo("davie504@link.org"))
                }.andDo(print())
                .andReturn()

        def parseResponse = objectMapper.
                readValue(response.getResponse().getContentAsString(), Map.class)

        def createdAuthor = authorRepository.findById((Long) parseResponse["id"])
        assertEquals(createdAuthor.get().getFirstName(), "Davie504")
        assertEquals(createdAuthor.get().getSecondName(), "Dave")
        assertEquals(createdAuthor.get().getFullName(), "Davie504 Dave")
        assertEquals(createdAuthor.get().getEmailAddress(), "davie504@link.org")
    }

    @Order(4)
    @Test
    void '004_should_don\'t_create_author_with_null_or_empty_fields'() {
        assertThrows(ServletException.class,
                { mockMvc.perform(post("/authors/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( """{
                                              "firstName" : "",
                                              "secondName" : "",
                                              "fullName" : null,
                                              "emailAddress" : "davie504@link.org" 
                                     }""")
                        .accept(MediaType.APPLICATION_JSON))})
    }

    @Order(5)
    @Test
    void '005_should_return_200_and_update_author'() {
        def response = mockMvc
                .perform(put("/authors/update/${author3.getId()}")
                .contentType(MediaType.APPLICATION_JSON)
                .content( """{
                                       "firstName" : "author301",
                                       "secondName" : "${author3.getSecondName()}",
                                       "fullName" : "${author3.getFullName()}",
                                       "emailAddress" : "${author3.getEmailAddress()}" 
                                     }""")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect {
                    status().isOk()
                    content().contentType(MediaType.APPLICATION_JSON)
                    jsonPath('$.firstName', equalTo("author301"))
                    jsonPath('$.secondName', equalTo(author3.getSecondName()))
                    jsonPath('$.fullName', equalTo(author3.getFullName()))
                    jsonPath('$.emailAddress', equalTo(author3.getEmailAddress()))
                }.andDo(print()).andReturn()

        def parseResponse = objectMapper.
                readValue(response.getResponse().getContentAsString(), Map.class)

        def updatedAuthor = authorRepository.findById((Long) parseResponse["id"])
        assertEquals(updatedAuthor.get().getFirstName(), "author301")
        assertEquals(updatedAuthor.get().getSecondName(), author3.getSecondName())
        assertEquals(updatedAuthor.get().getFullName(), author3.getFullName())
        assertEquals(updatedAuthor.get().getEmailAddress(), author3.getEmailAddress())
    }

    @Order(6)
    @Test
    void '006_should_don\'t_update_author_with_null_or_empty_fields'() {
        assertThrows(ServletException.class,
                { mockMvc.perform(put("/authors/update/${author3.getId()}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( """{
                                              "firstName" : "",
                                              "secondName" : "",
                                              "fullName" : null,
                                              "emailAddress" : "davie504@link.org" 
                                     }""")
                        .accept(MediaType.APPLICATION_JSON))})
    }

    @Order(7)
    @Test
    void '007_should_return_200_and_delete_author'() {
        mockMvc.perform(delete("/authors/delete/${author3.getId()}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())

        def deletedAuthor = authorRepository.findById(author3.getId())
        assertTrue(deletedAuthor.isEmpty())
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

    @AfterAll
    void resetDb() {
        authorRepository.deleteAll()
    }

    private final def equalsJson = [
            """[{"id":1,"firstName":"test","secondName":"author1","fullName":"test author1","emailAddress":"","postNames":[]},{"id":2,"firstName":"test","secondName":"author2","fullName":"test author2","emailAddress":"test_author2@tr.org","postNames":[]},{"id":3,"firstName":"test","secondName":"author3","fullName":"test author3","emailAddress":"test_author3@lm.org","postNames":[]}]"""]
}