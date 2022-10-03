package com.genspark.student.Auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.genspark.student.Dao.UserDao;
import com.genspark.student.Entity.SystemUser;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        SystemUser s = userDao.getUserByName(username);
        return new User(s.getName(), s.getPassword(), new ArrayList<GrantedAuthority>(List.of(new SimpleGrantedAuthority("ROLE_"+s.getRole()))));
    }
    
    
}
