package com.devsimple.brasileiraodigital.controller;

import com.devsimple.brasileiraodigital.dto.TeamDTO;
import com.devsimple.brasileiraodigital.model.Team;
import com.devsimple.brasileiraodigital.services.TeamService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/teams")
public class TeamController {

    private TeamService teamService;

    @ApiOperation("Listando todos os times! ")
    @GetMapping
    public ResponseEntity<List<TeamDTO>> listAll(){
        return ResponseEntity.ok().body(teamService.listAll());
    }

    @ApiOperation("Salvando os times no banco! ")
    @PostMapping
    public ResponseEntity<Team> save(@RequestBody TeamDTO teamDTO){
        teamService.save(teamDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Obtendo um time pela URI! ")
    @GetMapping("/{id}")
    public ResponseEntity<Team> findById(@PathVariable Long id){
        Team team = teamService.findById(id);
        return ResponseEntity.ok().body(team);
    }
}
