package org.example.libraryApi.user.service;

import lombok.RequiredArgsConstructor;
import org.example.libraryApi.user.domain.User;
import org.example.libraryApi.user.domain.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return new UserDetailsImpl(user);
    }
}
