package com.devsimple.brasileiraodigital.services;

import com.devsimple.brasileiraodigital.dto.TeamDTO;
import com.devsimple.brasileiraodigital.model.Team;
import com.devsimple.brasileiraodigital.repository.TeamRepository;
import com.devsimple.brasileiraodigital.services.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeamService {

    private TeamRepository teamRepository;

    public Team modelToDto(TeamDTO teamDTO){
        Team team = new Team();
        team.setId(teamDTO.getId());
        team.setName(teamDTO.getName());
        team.setAcronym(teamDTO.getAcronym());
        team.setStadium(teamDTO.getStadium());
        return team;
    }

    public TeamDTO dtoToModel(Team team){
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        teamDTO.setAcronym(team.getAcronym());
        teamDTO.setStadium(team.getStadium());
        return teamDTO;
    }

    @Transactional
    public Team save(TeamDTO teamDTO){
      Team team = modelToDto(teamDTO);
      team = teamRepository.save(team);
      return team;
    }

    @Transactional
    public List<TeamDTO> listAll(){
        return teamRepository.findAll()
                .stream()
                .map(x -> dtoToModel(x))
                .collect(Collectors.toList());
    }

    @Transactional
    public Team findById(Long id){
        return teamRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Time não encontrado!"));


    }

}
