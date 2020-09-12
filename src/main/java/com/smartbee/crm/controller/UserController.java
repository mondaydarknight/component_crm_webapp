package com.smartbee.crm.controller;

import com.smargbee.openapi_generated.crm.model.ApiUser;
import com.smartbee.crm.core.mappers.UserMapper;
import com.smartbee.crm.core.models.User;
import com.smartbee.crm.core.services.UserService;
import com.smartbee.openapi_generated.crm.api.UsersApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/crm/v1")
public class UserController implements UsersApi {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserMapper userMapper;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<ApiUser> createUser(@Valid @RequestBody ApiUser apiUser) {
        User newUser = userService.createUser(userMapper.toUser(apiUser));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        apiUser.setId(newUser.getId());
        return ResponseEntity.created(uri).body(apiUser);
    }
}
