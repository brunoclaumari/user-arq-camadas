package com.layer_arch.userdemo.service;

import com.layer_arch.userdemo.model.request.UserRequestDto;
import com.layer_arch.userdemo.model.response.UserResponseDto;

import java.util.List;

public interface IUserService {

    List<UserResponseDto> findAll();

    UserResponseDto create(UserRequestDto userRequestDto);
}
