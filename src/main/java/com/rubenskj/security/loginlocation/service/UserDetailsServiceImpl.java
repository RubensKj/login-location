package com.rubenskj.security.loginlocation.service;

import com.rubenskj.security.loginlocation.model.User;
import com.rubenskj.security.loginlocation.model.UserDetailsImpl;
import com.rubenskj.security.loginlocation.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserRepository userRepository;

    public UserDetailsServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Sorry, user not found with this e-mail. E-mail: " + email));

        return UserDetailsImpl.build(user);
    }
}
