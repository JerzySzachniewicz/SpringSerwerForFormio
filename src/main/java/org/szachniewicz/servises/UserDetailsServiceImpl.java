package org.szachniewicz.servises;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.szachniewicz.model.ApplicationUser;

import java.util.HashSet;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var applicationUser = new ApplicationUser();
        applicationUser.setId(1);
        applicationUser.setPassword("$2a$10$hpVK./znpYpYzucdNIuQeOxZs90vwXZOF3CELyC6DTNzcjmI6jT9i");
        applicationUser.setName("test");
        return getSpringUser(applicationUser);
    }

    private User getSpringUser(ApplicationUser user) {
        return new User(user.getName(), user.getPassword(), new HashSet<>());
    }

    private HashSet<GrantedAuthority> getUserAuthorities(ApplicationUser applicationUser) {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + applicationUser.getRole().getName().toUpperCase());
        HashSet<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(authority);
        return authorities;
    }
}