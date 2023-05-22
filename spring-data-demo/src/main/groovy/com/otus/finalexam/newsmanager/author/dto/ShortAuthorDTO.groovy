package com.otus.finalexam.newsmanager.author.dto

class ShortAuthorDTO {

    private Long id
    private String fullName
    private String emailAddress

    ShortAuthorDTO() {
    }

    ShortAuthorDTO(Long id, String fullName, String emailAddress) {
        this.id = id
        this.fullName = fullName
        this.emailAddress = emailAddress
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
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

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false

        ShortAuthorDTO that = (ShortAuthorDTO) o

        if (emailAddress != that.emailAddress) return false
        if (fullName != that.fullName) return false
        if (id != that.id) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0)
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0)
        return result
    }


    @Override
    String toString() {
        return "ShortAuthorDTO{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}'
    }
}

