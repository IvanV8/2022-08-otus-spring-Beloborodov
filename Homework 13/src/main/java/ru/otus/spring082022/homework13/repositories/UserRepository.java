package ru.otus.spring082022.homework13.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring082022.homework13.domain.LibraryUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<LibraryUser, Long> {
    Optional<LibraryUser> findById(long id);

    LibraryUser findUserByLoginIs(String login);


}
