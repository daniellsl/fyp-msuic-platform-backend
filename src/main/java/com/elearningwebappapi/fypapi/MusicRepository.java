package com.elearningwebappapi.fypapi;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    List<Music> findByMid(long mid);
    List<Music> findByUid(long uid);
}