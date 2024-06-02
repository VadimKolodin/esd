package com.example.testmp.user.data;

import com.example.testmp.security.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByLoginIgnoreCase(String login);

	List<UserEntity> findAllByRole(Authority role);

}