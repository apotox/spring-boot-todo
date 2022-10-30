package com.safi.myfirst.service;

import com.safi.myfirst.dao.UserRepository;
import com.safi.myfirst.domaino.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService
{


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    public UserService()
    {
    }


    public Long create(User u){

        u.setPassword(passwordEncoder.encode(u.getPassword()));

        User user = userRepository.save(u);

        return  user.getId();
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User u = userRepository.findByUsername(username);

        if(u == null){
            throw  new UsernameNotFoundException("username not found!");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        Arrays.stream(u.getRoles()).forEach(r -> {
             authorities.add(new SimpleGrantedAuthority(r));
        });

        return new org.springframework.security.core.userdetails.User(u.getUsername(),u.getPassword(), authorities);
    }
}
