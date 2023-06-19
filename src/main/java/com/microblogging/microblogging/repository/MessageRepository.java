package com.microblogging.microblogging.repository;

import com.microblogging.microblogging.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Message entities in the database.
 * This interface extends JpaRepository to inherit common database operations.
 */
@Repository
public interface MessageRepository extends JpaRepository<Messages, Long> {
}
