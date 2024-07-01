package com.company.apparticle.repository;

import com.company.apparticle.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByName(String name);

}