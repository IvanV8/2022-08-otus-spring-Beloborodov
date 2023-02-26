package ru.otus.spring082022.homework13.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring082022.homework13.domain.LibraryUser;
import ru.otus.spring082022.homework13.domain.UserRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id = -1;
    private String login;
    private String firstname;
    private String lastname;

    public static UserDto toDto(LibraryUser user) {
        return new UserDto(user.getId(), user.getLogin(), user.getFirstname(), user.getLastname());
    }

    public static LibraryUser toDomain(UserDto user) {
        return new LibraryUser(user.getId(), user.getLogin(), "", user.getFirstname(), user.getLastname(),
                new UserRole());
    }

    @Override
    public String toString() {
        return this.firstname + " " + this.lastname;
    }

}
