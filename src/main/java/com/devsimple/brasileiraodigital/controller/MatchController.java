package com.devsimple.brasileiraodigital.controller;

import com.devsimple.brasileiraodigital.dto.MatchDTO;
import com.devsimple.brasileiraodigital.model.Match;
import com.devsimple.brasileiraodigital.services.MatchService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/matches")
@AllArgsConstructor
public class MatchController {

    private MatchService matchService;

    @ApiOperation(value = "Gera os jogos do campeonato")
    @PostMapping
    public ResponseEntity<Void> generateMatch(){
        matchService.generateMatch(LocalDateTime.now(), Arrays.asList());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Listando todos os jogos")
    @GetMapping
    public ResponseEntity<List<MatchDTO>> findAll(){
        return ResponseEntity.ok().body(matchService.findAll());
    }
}
