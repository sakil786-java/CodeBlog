package com.blogging.app.repositories;

import com.blogging.app.enitities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
}
