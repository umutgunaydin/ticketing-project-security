package com.company.service.impl;

import com.company.entity.User;
import com.company.entity.common.UserPrincipal;
import com.company.repository.UserRepository;
import com.company.service.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserNameAndIsDeleted(username,false);

        if(user==null){
            throw new UsernameNotFoundException(username);
        }


        return new UserPrincipal(user);  //get the user from db,and convert to user springs understands by using userprincipal

    }
}