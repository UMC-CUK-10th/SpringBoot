package com.example.springboot.global.security;

import com.example.springboot.domain.users.entity.Users;
import com.example.springboot.domain.users.repository.UsersRepository;
import com.example.springboot.global.security.entity.AuthUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Users users = usersRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        return new AuthUsers(users);
    }
}
