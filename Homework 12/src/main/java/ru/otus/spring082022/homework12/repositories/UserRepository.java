package ru.otus.spring082022.homework12.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring082022.homework12.domain.LibraryUser;

;

@Repository
public interface UserRepository extends JpaRepository<LibraryUser, Long> {

  LibraryUser findUserByLoginIs(String login);
}
