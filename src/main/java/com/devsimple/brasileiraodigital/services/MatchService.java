package com.devsimple.brasileiraodigital.services;

import com.devsimple.brasileiraodigital.dto.MatchDTO;
import com.devsimple.brasileiraodigital.dto.StandingsDTO;
import com.devsimple.brasileiraodigital.model.Match;
import com.devsimple.brasileiraodigital.model.Team;
import com.devsimple.brasileiraodigital.repository.MatchRepository;
import com.devsimple.brasileiraodigital.repository.TeamRepository;
import com.devsimple.brasileiraodigital.services.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
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
       matchDTO.setPartida(match.getId());
       matchDTO.setDate(match.getDate());
       matchDTO.setStadium(match.getTeam1().getStadium());
       matchDTO.setFinished(match.getFinished());
       matchDTO.setGoalsHome(match.getGoalsHome());
       matchDTO.setGoalsVisited(match.getGoalsVisited());
       matchDTO.setRound(match.getRound());
       matchDTO.setHome(teamService.dtoToModel(match.getTeam1()).getName());
       matchDTO.setVisited(teamService.dtoToModel(match.getTeam2()).getName());
       return matchDTO;
    }

    public void generateMatches(LocalDateTime roundOne, List<LocalDate> dateInvalid){
        final List<Team> teams = teamRepository.findAll(); //recupera todos os times

        List<Team> teamOne = new ArrayList<>(); //lista times do primeiro turno
        List<Team> teamTwo = new ArrayList<>(); //lista times do segundo turno

        teamOne.addAll(teams); //adiciona os times no primeiro turno
        teamTwo.addAll(teams); //adiciona os times no segundo turno

        matchRepository.deleteAll();

        List<Match> matches = new ArrayList<>();
        int t = teams.size();
        int m = teams.size() / 2;
        Integer round = 0;
        LocalDateTime matchDate = roundOne;

        for (int i = 0; i < t - 1; i++) {
            round = i + 1;
            for (int j = 0; j < m; j++) {
                //Teste para ajustar o mando de campo
                Team time1;
                Team time2;
                if (j % 2 == 1 || i % 2 == 1 && j == 0) {
                    time1 = teams.get(t - j - 1);
                    time2 = teams.get(j);
                } else {
                    time1 = teams.get(j);
                    time2 = teams.get(t - j - 1);
                }
                if (time1 == null) {
                    System.out.println("Time  1 null");
                }
                matches.add(generateMatch(matchDate, round, time1, time2));
                matchDate = matchDate.plusDays(7);
            }
            teams.add(1, teams.remove(teams.size() -1)); //gira s times, mantendo o primeiro lugar
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

    public MatchDTO findById(Long id){
        return modelToDTO(matchRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Match not found")));
    }

    public MatchDTO endMatch(Long id) {
        Optional<Match> matchPresent = matchRepository.findById(id);
        if (matchPresent.isPresent()){
            final Match match = matchPresent.get();
            match.setFinished(true);
            return modelToDTO(matchRepository.save(match));
        }else {
            throw new ObjectNotFoundException("Match not exist!");
        }
    }

    public StandingsDTO findStandings() {
        StandingsDTO standingsDTO = new StandingsDTO();

        final List<Team> teams = teamRepository.findAll();

        teams.forEach(team -> {
            final List<Match> matchHome = matchRepository.findTeamOneAndFinished(team, true);
            final List<Match> matchVisited = matchRepository.findTeamTwoAndFinished(team, true);
            AtomicReference<Integer> win = new AtomicReference<>(0);
            AtomicReference<Integer> defeat = new AtomicReference<>(0);
            AtomicReference<Integer> draws = new AtomicReference<>(0);
            AtomicReference<Integer> goals = new AtomicReference<>(0);
            AtomicReference<Integer> goalsAllowed = new AtomicReference<>(0);

            matchHome.forEach(match -> {
                if (match.getGoalsHome() < match.getGoalsVisited()){
                    win.getAndSet(win.get() + 1);
                }
                if (match.getGoalsHome() > match.getGoalsVisited()){
                    defeat.getAndSet(defeat.get() + 1);
                }else {
                    draws.getAndSet(draws.get() + 1);
                }
                goals.set(goals.get() + match.getGoalsHome());
                goalsAllowed.set(goalsAllowed.get() + match.getGoalsVisited());
            });

        });


    }
}
