package org.example.repository;

import org.example.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    @Query("from Content where title = :title")
    Optional<Content> findByTitle(String title);
}
