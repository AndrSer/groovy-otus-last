package com.otus.finalexam.newsmanager.post.dto

import com.otus.finalexam.newsmanager.author.dto.ShortAuthorDTO

import java.time.LocalDateTime

class PostDTO {

    private Long id
    private String name
    private String header
    private String text
    private LocalDateTime publishDate
    private int rating
    private ShortAuthorDTO author

    PostDTO() {
    }

    PostDTO(Long id, String name, String header, String text,
            LocalDateTime publishDate, int rating, ShortAuthorDTO author) {
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

    String getPublishDate() {
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

    ShortAuthorDTO getAuthor() {
        return author
    }

    void setAuthor(ShortAuthorDTO author) {
        this.author = author
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false

        PostDTO postDTO = (PostDTO) o

        if (rating != postDTO.rating) return false
        if (author != postDTO.author) return false
        if (header != postDTO.header) return false
        if (id != postDTO.id) return false
        if (name != postDTO.name) return false
        if (publishDate != postDTO.publishDate) return false
        if (text != postDTO.text) return false

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
        result = 31 * result + (author != null ? author.hashCode() : 0)
        return result
    }


    @Override
    String toString() {
        return "PostDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", header='" + header + '\'' +
                ", text='" + text + '\'' +
                ", publishDate=" + publishDate +
                ", rating=" + rating +
                ", author=" + author +
                '}'
    }
}


