package com.otus.finaletask.newsmanager.post


import com.otus.finaletask.newsmanager.author.AuthorType
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "post", schema = "public")
class PostType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id

    @Column(name = "name", nullable = false)
    private String name

    @Column(name = "header", nullable = false)
    private String header

    @Column(name = "text", nullable = false)
    private String text

    @Column(name = "publish_date", nullable = false)
    private String publishDate

    @Column(name = "rating")
    private String rating

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.DETACH])
    @JoinColumn(name = "author_id", nullable = false)
    private AuthorType user
}
