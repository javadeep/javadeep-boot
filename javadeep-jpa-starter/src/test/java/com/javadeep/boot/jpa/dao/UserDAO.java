package com.javadeep.boot.jpa.dao;

import com.javadeep.boot.jpa.domain.User;
import com.javadeep.boot.jpa.repository.JavadeepRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDAO extends JavadeepRepository<User, Long> {
}
