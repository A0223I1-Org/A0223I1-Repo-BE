package com.codegym.a0223i1_pharmacy_professional_be.service.implement.accountmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.repository.accountmanagement.UserRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.accountmanagement.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserDetailsService userDetailsService(){
        return  new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username)
                        .orElseThrow(()->new UsernameNotFoundException("User not found"));
            }
        };
    }
}
