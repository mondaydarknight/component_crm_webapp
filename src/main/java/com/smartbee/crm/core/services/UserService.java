package com.smartbee.crm.core.services;

import com.smartbee.crm.core.exceptions.LoginIdAlreadyUsedException;
import com.smartbee.crm.core.models.User;
import com.smartbee.crm.core.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) throws LoginIdAlreadyUsedException {
        userRepository.findOneByLoginId(user.getLoginId()).ifPresent(existingUser -> {
            throw new LoginIdAlreadyUsedException();
        });
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        User newUser = userRepository.save(user);
        LOG.debug("Created user information: {}", newUser);
        return newUser;
    }
}
