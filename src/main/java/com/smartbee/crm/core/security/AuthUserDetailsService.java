package com.smartbee.crm.core.security;

import com.smartbee.crm.core.models.User;
import com.smartbee.crm.core.repositories.UserRepository;
import org.hibernate.annotations.FetchProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component("userDetailsService")
public class AuthUserDetailsService implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthUserDetailsService.class);

    private final UserRepository userRepository;

    public AuthUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String loginId) throws UsernameNotFoundException {
        LOG.debug("Authentication username {}", loginId);
        String lowercaseLoginId = loginId.toLowerCase(Locale.ENGLISH);
        Optional<User> userFromDatabase = userRepository.findOneWithAuthorityByLoginId(lowercaseLoginId);
        return userFromDatabase.map(user -> {
            LOG.debug("User role authority is {}", user.getAuthority());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getAuthority()));
            return new org.springframework.security.core.userdetails.User(lowercaseLoginId, user.getPassword(), grantedAuthorities);
        }).orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLoginId + " was not found in database"));
    }
}
