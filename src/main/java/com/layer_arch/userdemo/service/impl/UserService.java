package com.layer_arch.userdemo.service.impl;

import com.layer_arch.userdemo.mapper.UserMapper;
import com.layer_arch.userdemo.model.request.UserRequestDto;
import com.layer_arch.userdemo.model.response.UserResponseDto;
import com.layer_arch.userdemo.repository.IUserRepository;
import com.layer_arch.userdemo.repository.entity.UserEntity;
import com.layer_arch.userdemo.service.IUserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository repository;

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Transactional(readOnly = true)
    @Override
    public List<UserResponseDto> findAll() {

        var users = repository.findAll();
        return this.mapper.toListUserResponse(users);
    }

    @Transactional
    @Override
    public UserResponseDto create(UserRequestDto userRequestDto) {
        UserEntity user = mapper.toUserEntity(userRequestDto);
        UserEntity userSaved = repository.save(user);

        return mapper.toResponse(userSaved);
    }
}
