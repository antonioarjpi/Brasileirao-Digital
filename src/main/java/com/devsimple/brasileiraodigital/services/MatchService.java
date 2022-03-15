package com.devsimple.brasileiraodigital.services;

import com.devsimple.brasileiraodigital.dto.MatchDTO;
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
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MatchService {

    private MatchRepository matchRepository;
    private TeamRepository teamRepository;
    private TeamService teamService;

    public List<MatchDTO> findAll(){
        return matchRepository.findAll()
                .stream()
                .map(x -> modelToDTO(x))
                .collect(Collectors.toList());
    }

    public MatchDTO modelToDTO(Match match) {
       MatchDTO matchDTO = new MatchDTO();
       matchDTO.setId(match.getId());
       matchDTO.setDate(match.getDate());
       matchDTO.setStadium(match.getTeam1().getStadium());
       matchDTO.setFinished(match.getFinished());
       matchDTO.setGoalsHome(match.getGoalsHome());
       matchDTO.setGoalsVisited(match.getGoalsVisited());
       matchDTO.setRound(match.getRound());
       matchDTO.setHome(match.getTeam1().getName());
       matchDTO.setVisited(match.getTeam2().getName());
       return matchDTO;
    }

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

    public Match generateMatch(LocalDateTime dayMatch, Integer round, Team team1, Team team2) {
        Match match = new Match();
        Random random = new Random();
        match.setTeam1(team1);
        match.setTeam2(team2);
        match.setDate(dayMatch);
        match.setRound(round);
        match.setGoalsHome(random.nextInt(5));
        match.setGoalsVisited(random.nextInt(5));
        match.setFinished(false);
        return match;
    }

}
