package com.devsimple.brasileiraodigital.repository;

import com.devsimple.brasileiraodigital.model.Match;
import com.devsimple.brasileiraodigital.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findTeamOneAndFinished(Team team1, boolean finished);
    List<Match> findTeamTwoAndFinished(Team team2, boolean finished);
    List<Match> findAllMatchesFinished();
}
