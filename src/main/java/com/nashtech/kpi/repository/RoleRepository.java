package com.nashtech.kpi.repository;

import com.nashtech.kpi.entity.RoleEntity;
import com.nashtech.kpi.model.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByRole(String role);

}
