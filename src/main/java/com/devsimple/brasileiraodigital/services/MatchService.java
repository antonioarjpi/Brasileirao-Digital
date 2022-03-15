package com.devsimple.brasileiraodigital.services;

import com.devsimple.brasileiraodigital.model.Match;
import com.devsimple.brasileiraodigital.model.Team;
import com.devsimple.brasileiraodigital.repository.MatchRepository;
import com.devsimple.brasileiraodigital.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MatchService {

    private MatchRepository matchRepository;
    private TeamRepository teamRepository;
    private TeamService teamService;

    public void generateMatch(LocalDateTime roundOne, List<LocalDate> dateInvalid){
        final List<Team> teams = teamRepository.findAll();
        List<Team> teamOne = new ArrayList<>();
        List<Team> teamTwo = new ArrayList<>();
        teamOne.addAll(teams);
        teamTwo.addAll(teams);

        matchRepository.deleteAll();

        List<Match> matches = new ArrayList<>();

        int t = teams.size();
        int m = teams.size() / 2;

        Integer round = 0;
        LocalDateTime matchDate = roundOne;

        for (int i=0; i<t; i++){
            round = i + 1;
            for (int j=0; j<m; j++){
                Team team1;
                Team team2;
                if (j % 2 == 1 || i % 2 == 1 && j == 0) {
                    team1 = teams.get(t - j - 1);
                    team2 = teams.get(j);
                }else {
                    team1 = teams.get(j);
                    team2 = teams.get(t - j - 1);
                }
                if (team1 == null){
                    System.out.println("Não existe time!");
                }
                matches.add(generateMatch(matchDate, round, team1, team2));
            }
            teams.add(1, teams.remove(teams.size() -1));
        }

        matches.forEach(match -> System.out.println(match));

        matchRepository.saveAll(matches);

        List<Match> matches2 = new ArrayList<>();

        matches.forEach(match -> {
            Team team1 = match.getTeam2();
            Team team2 = match.getTeam1();
            matches2.add(generateMatch(match.getDate().plusDays(7 * matches.size()),
                    match.getRound() + matches.size(), team1, team2));
        });
        matchRepository.saveAll(matches2);

    }

    private Match generateMatch(LocalDateTime dayMatch, Integer round, Team team1, Team team2) {
        Match match = new Match();
        match.setTeam1(team1);
        match.setTeam2(team2);
        match.setDate(dayMatch);
        match.setRound(round);
        match.setGoalsHome(2);
        match.setGoalsVisited(1);
        match.setFinished(false);
        return match;
    }


}
