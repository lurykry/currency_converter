package ru.smsoft.currencyconverter.model;

import ru.smsoft.currencyconverter.config.security.Roles;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_login")
    @NotNull
    @Size(
            min = 3,
            max = 32,
            message = "Login must be between 3 and 32 characters long"
    )
    private String userLogin;

    @Column(name = "user_email")
    @NotNull
    private String userEmail;

    @Column(name = "user_password")
    private String userPassword;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "roles")
    @Column(name = "role_name")
    private Set<Roles> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "user")
    private List<ConversionHistory> conversionHistory = new ArrayList<>();

    public User() {
    }

    public User(Long userId, @NotNull String userLogin,
                @NotNull String userEmail,
                String userPassword, Set<Roles> roles) {
        this.userId = userId;
        this.userLogin = userLogin;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    private void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public List<ConversionHistory> getConversionHistory() {
        return conversionHistory;
    }

    private void setConversionHistory(List<ConversionHistory> conversionHistory) {
        this.conversionHistory = conversionHistory;
    }
}