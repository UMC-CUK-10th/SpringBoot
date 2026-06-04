package com.example.springboot.global.security;

import com.example.springboot.domain.users.entity.Users;
import com.example.springboot.domain.users.exception.UsersErrorCode;
import com.example.springboot.domain.users.exception.UsersException;
import com.example.springboot.domain.users.repository.UsersRepository;
import com.example.springboot.global.security.entity.AuthUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    public UserDetails loadUserByEmail(String email)
            throws UsernameNotFoundException {

        Users users = usersRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsersException(UsersErrorCode.USERS_NOT_FOUND));

        return new AuthUsers(users);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Users users = usersRepository.findByEmail(username)
                .or(() -> usersRepository.findByUsername(username))
                .orElseThrow(() ->
                        new UsersException(UsersErrorCode.USERS_NOT_FOUND));

        return new AuthUsers(users);
    }
}
