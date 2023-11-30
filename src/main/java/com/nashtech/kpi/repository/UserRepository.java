package com.nashtech.kpi.repository;

import com.nashtech.kpi.dto.UserDTO;
import com.nashtech.kpi.entity.UserEntity;
import com.nashtech.kpi.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByProvider(Provider provider);

    List<UserDTO> findBy(Class<UserDTO> userDTOClass);

    List<UserEntity> findByEnabled(boolean enable);

}
