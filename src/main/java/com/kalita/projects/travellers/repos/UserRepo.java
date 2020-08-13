package com.kalita.projects.travellers.repos;

import com.kalita.projects.travellers.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
}
