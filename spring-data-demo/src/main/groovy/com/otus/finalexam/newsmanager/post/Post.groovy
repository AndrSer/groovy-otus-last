package com.otus.finalexam.newsmanager.post

import com.fasterxml.jackson.annotation.JsonIgnore
import com.otus.finalexam.newsmanager.author.Author
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank

import java.time.LocalDateTime

@Entity
@Table(name = "post", schema = "public")
class Post {

    @Id
    @SequenceGenerator(name="pk_sequence", sequenceName="post_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pk_sequence")
    @Column(name = "id")
    private Long id

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name

    @NotBlank
    @Column(name = "header", nullable = false)
    private String header

    @Column(name = "text", nullable = false)
    private String text

    @Column(name = "publish_date", nullable = false)
    private LocalDateTime publishDate

    @Column(name = "rating")
    private int rating = 0

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "author_id",
            foreignKey = @ForeignKey(name = "fk_posts_author_id"))
    private Author author

    Post() {
    }

    Post(Long id, String name, String header, String text,
         LocalDateTime publishDate, int rating, Author author) {
        this.id = id
        this.name = name
        this.header = header
        this.text = text
        this.publishDate = publishDate
        this.rating = rating
        this.author = author
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getHeader() {
        return header
    }

    void setHeader(String header) {
        this.header = header
    }

    String getText() {
        return text
    }

    void setText(String text) {
        this.text = text
    }

    LocalDateTime getPublishDate() {
        return publishDate
    }

    void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate
    }

    int getRating() {
        return rating
    }

    void setRating(int rating) {
        this.rating = rating
    }

    Author getAuthor() {
        return author
    }

    void setAuthor(Author author) {
        this.author = author
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false

        Post postType = (Post) o

        if (rating != postType.rating) return false
        if (header != postType.header) return false
        if (id != postType.id) return false
        if (name != postType.name) return false
        if (publishDate != postType.publishDate) return false
        if (text != postType.text) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (name != null ? name.hashCode() : 0)
        result = 31 * result + (header != null ? header.hashCode() : 0)
        result = 31 * result + (text != null ? text.hashCode() : 0)
        result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0)
        result = 31 * result + rating
        return result
    }


    @Override
    String toString() {
        return "PostType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", header='" + header + '\'' +
                ", text='" + text + '\'' +
                ", publishDate=" + publishDate +
                ", rating=" + rating +
                '}'
    }
}
