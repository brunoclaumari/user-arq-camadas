package com.layer_arch.userdemo.repository;

import com.layer_arch.userdemo.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, UUID> {
}
