package ru.task.library_spring.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String login;
    private String password;

    @OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL)
    private List<Book> bookList;

    public User() {}

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @PreRemove
    private void preRemove() {
        for (Book s : bookList) {
            s.setWho_take(null);
        }
    }
}