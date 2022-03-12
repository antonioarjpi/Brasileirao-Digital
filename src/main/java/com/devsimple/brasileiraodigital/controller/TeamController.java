package com.devsimple.brasileiraodigital.controller;

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
    public ResponseEntity<List<Team>> listAll(){
        List<Team> team = teamService.listAll();
        return ResponseEntity.ok(team);
    }

    @ApiOperation("Salvando os times no banco! ")
    @PostMapping
    public Team save(@RequestBody Team team){
        return teamService.save(team);
    }

    @ApiOperation("Obtendo um time pela URI! ")
    @GetMapping("/{id}")
    public ResponseEntity<Team> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(teamService.findById(id));
    }
}
