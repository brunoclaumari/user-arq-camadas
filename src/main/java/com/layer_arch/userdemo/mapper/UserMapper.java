package com.layer_arch.userdemo.mapper;

import com.layer_arch.userdemo.model.request.UserRequestDto;
import com.layer_arch.userdemo.model.response.UserResponseDto;
import com.layer_arch.userdemo.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserEntity toUserEntity(UserRequestDto userRequestDto);

    UserEntity toUserEntity(UserResponseDto userResponseDto);

    UserRequestDto toRequest(UserEntity userEntity);

    UserResponseDto toResponse(UserEntity userEntity);

    default List<UserResponseDto> toListUserResponse(List<UserEntity> users){

        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        if (users != null){
            users.stream().forEach(u ->{
                userResponseDtoList.add(toResponse(u));
            });
        }

        return userResponseDtoList;
    }
}
