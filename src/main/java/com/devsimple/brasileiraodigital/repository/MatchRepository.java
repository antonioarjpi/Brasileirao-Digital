package com.devsimple.brasileiraodigital.repository;

import com.devsimple.brasileiraodigital.model.Match;
import com.devsimple.brasileiraodigital.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByTeam1AndFinished(Team team, boolean finished);
    List<Match> findByTeam2AndFinished(Team team, boolean finished);

}
