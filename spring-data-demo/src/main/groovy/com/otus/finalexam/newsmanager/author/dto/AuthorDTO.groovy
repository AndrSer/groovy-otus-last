package com.otus.finalexam.newsmanager.author.dto

class AuthorDTO {

    private Long id
    private String firstName
    private String secondName
    private String fullName
    private String emailAddress
    private Set<String> postNames

    AuthorDTO() {
    }

    AuthorDTO(Long id, String firstName, String secondName,
              String fullName, String emailAddress, Set<String> postNames) {
        this.firstName = firstName
        this.secondName = secondName
        this.fullName = fullName
        this.emailAddress = emailAddress
        this.postNames = postNames
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


    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    void setPostNames(Set<String> postNames) {
        this.postNames = postNames
    }

    Set<String> getPostNames() {
        return postNames
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false

        AuthorDTO that = (AuthorDTO) o

        if (emailAddress != that.emailAddress) return false
        if (firstName != that.firstName) return false
        if (fullName != that.fullName) return false
        if (secondName != that.secondName) return false

        return true
    }

    int hashCode() {
        int result
        result = (firstName != null ? firstName.hashCode() : 0)
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0)
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0)
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0)
        return result
    }

    @Override
    String toString() {
        return "AuthorInputDTO{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}'
    }
}
