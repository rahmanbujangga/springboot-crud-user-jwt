package com.bujangga.springdemo.service.impl;

import com.bujangga.springdemo.io.entity.UserEntity;
import com.bujangga.springdemo.repository.UserRepository;
import com.bujangga.springdemo.service.UserService;
import com.bujangga.springdemo.shared.dto.UserDto;
import com.bujangga.springdemo.shared.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserUtils userUtils;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserUtils userUtils, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userUtils = userUtils;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto createUser(UserDto user) {

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        String publicUserId = userUtils.genereatedUserId(30);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setUserId(publicUserId);
        UserEntity storedUserDetails = userRepository.save(userEntity);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);
        return returnValue;

    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findUserByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
