package ru.otus.spring082022.homework12.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.spring082022.homework12.domain.LibraryUser;
import ru.otus.spring082022.homework12.repositories.UserRepository;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LibraryUser user = userRepository.findUserByLoginIs(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Name " + username + "Not Found");
        }
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());


    }
}
