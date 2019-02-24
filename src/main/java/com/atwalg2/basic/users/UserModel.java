package com.atwalg2.basic.users;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.io.Serializable;

@Entity
@JsonFilter("UserRepositoryFilter")
@Table(name = "users")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="givenName", nullable = false)
    private String givenName;

    @Column(name="surname", nullable = false)
    private String surname;

    // @JsonIgnore
    @Column(name="countryCode", nullable = false)
    private String countryCode;

//    @OneToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name="something_id", nullable = false)
//    private Something something;

    public UserModel() {
    }

    public UserModel(String email, String givenName, String surname, String countryCode) {
        this.email = email;
        this.givenName = givenName;
        this.surname = surname;
        this.countryCode = countryCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}