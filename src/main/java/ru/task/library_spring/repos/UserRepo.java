package ru.task.library_spring.repos;

import org.springframework.data.repository.CrudRepository;
import ru.task.library_spring.entity.User;

public interface UserRepo extends CrudRepository<User, Long> {
    public User findByLogin(String login);
}
