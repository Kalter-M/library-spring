package ru.task.library_spring.entity;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private String isbn;
    private String author;
    private String name;

    public Book() {}

    public Book(String isbn, String name, String author)
    {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.user_id = null;
    }

    public User getWho_take() {
        return user_id;
    }

    public void setWho_take(User who_take) {
        this.user_id = who_take;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user_id;


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
