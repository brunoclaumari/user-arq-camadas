package com.layer_arch.userdemo.controller;

import com.layer_arch.userdemo.model.request.UserRequestDto;
import com.layer_arch.userdemo.model.response.UserResponseDto;
import com.layer_arch.userdemo.service.impl.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(){

        var listDto = service.findAll();

        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserRequestDto userRequestDto){

        UserResponseDto dto = service.create(userRequestDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }
}
