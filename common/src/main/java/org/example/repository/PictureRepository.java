package org.example.repository;

import org.example.models.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {
    List<Picture> findByRequestId(String requestId);

    @Query("SELECT f FROM Picture f WHERE f.username = :user")
    List<Picture> findByUser(String user);
}
