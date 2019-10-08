package com.magnoliaory.hyrule.repository;

import com.magnoliaory.hyrule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
