package com.lil.pretty.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, String> {
	Optional<User> findByUsNm(String usNm);
	Optional<User> findByUsId(String usId);

}