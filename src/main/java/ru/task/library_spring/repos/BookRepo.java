package ru.task.library_spring.repos;

import org.springframework.data.repository.CrudRepository;
import ru.task.library_spring.entity.Book;

public interface BookRepo extends CrudRepository<Book, Long>{
    public Book findByIsbn(String isbn);
}
