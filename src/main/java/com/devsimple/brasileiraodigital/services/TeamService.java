package com.devsimple.brasileiraodigital.services;

import com.devsimple.brasileiraodigital.model.Team;
import com.devsimple.brasileiraodigital.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {

    private TeamRepository teamRepository;

    @Transactional
    public Team save(Team team){
        return teamRepository.save(team);
    }

    @Transactional
    public List<Team> listAll(){
        return teamRepository.findAll();
    }

    @Transactional
    public Team findById(Long id){
        return teamRepository.findById(id).get();
    }

}
