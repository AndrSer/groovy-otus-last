package com.otus.finalexam.newsmanager.author

import com.otus.finalexam.newsmanager.post.Post
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank


@Entity
@Table(name = "author", schema = "public")
class Author {

    @Id
    @SequenceGenerator(name="pk_sequence", sequenceName="author_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pk_sequence")
    @Column(name = "id")
    private Long id

    @NotBlank
    @Column(name = "first_name", nullable = false)
    private String firstName

    @NotBlank
    @Column(name = "second_name", nullable = false)
    private String secondName

    @NotBlank
    @Column(name = "full_name", nullable = false)
    private String fullName

    @Column(name = "email_address")
    private String emailAddress

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Post> posts

    Author() {}

    Author(Long id, String firstName, String secondName, String fullName,
           String emailAddress, Set<Post> posts) {
        this.id = id
        this.firstName = firstName
        this.secondName = secondName
        this.fullName = fullName
        this.emailAddress = emailAddress
        this.posts = posts
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getFirstName() {
        return firstName
    }

    void setFirstName(String firstName) {
        this.firstName = firstName
    }

    String getSecondName() {
        return secondName
    }

    void setSecondName(String secondName) {
        this.secondName = secondName
    }

    String getFullName() {
        return fullName
    }

    void setFullName(String fullName) {
        this.fullName = fullName
    }

    String getEmailAddress() {
        return emailAddress
    }

    void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress
    }

    Set<Post> getPosts() {
        return posts
    }

    void setPosts(Set<Post> posts) {
        this.posts = posts
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false

        Author that = (Author) o

        if (emailAddress != that.emailAddress) return false
        if (firstName != that.firstName) return false
        if (fullName != that.fullName) return false
        if (id != that.id) return false
        if (secondName != that.secondName) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0)
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0)
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0)
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0)
        return result
    }


    @Override
    String toString() {
        return "AuthorType{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", emailAddress='" + emailAddress +
                '}'
    }
}
