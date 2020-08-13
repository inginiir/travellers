package com.kalita.projects.travellers.repos;

import com.kalita.projects.travellers.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
