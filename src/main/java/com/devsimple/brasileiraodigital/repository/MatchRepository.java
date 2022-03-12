package com.devsimple.brasileiraodigital.repository;

import com.devsimple.brasileiraodigital.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
