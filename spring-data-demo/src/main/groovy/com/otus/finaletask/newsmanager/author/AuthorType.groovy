package com.otus.finaletask.newsmanager.author

import com.otus.finaletask.newsmanager.post.PostType
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
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
@Table(name = "author", schema = "public")
class AuthorType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id

    @Column(name = "first_name", nullable = false)
    private String firstName

    @Column(name = "second_name", nullable = false)
    private String secondName

    @Column(name = "full_name", nullable = false)
    private String fullName

    @Column(name = "email_address")
    private String emailAddress

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.DETACH])
    private Set<PostType> post
}
