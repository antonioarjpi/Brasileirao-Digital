package com.devsimple.brasileiraodigital.controller;

import com.devsimple.brasileiraodigital.dto.EndMatchDTO;
import com.devsimple.brasileiraodigital.dto.MatchDTO;
import com.devsimple.brasileiraodigital.services.MatchService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/matches")
@AllArgsConstructor
public class MatchController {

    private MatchService matchService;

    @ApiOperation(value = "Gera os jogos do campeonato")
    @PostMapping("/generate-matches")
    public ResponseEntity<Void> generateMatch(){
        matchService.generateMatches(LocalDateTime.now(), Arrays.asList());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Listando todos os jogos")
    @GetMapping
    public ResponseEntity<List<MatchDTO>> findAll(){
        return ResponseEntity.ok().body(matchService.findAll());
    }

    @ApiOperation(value = "Mostra um jogo")
    @GetMapping("/{id}")
    public ResponseEntity<MatchDTO> findbyId(@PathVariable Long id){
        return ResponseEntity.ok().body(matchService.findById(id));
    }

    @ApiOperation(value = "Finalizando jogo")
    @PostMapping("/endmatch/{id}")
    public ResponseEntity<MatchDTO> endmatch(@PathVariable Long id){
        return ResponseEntity.ok().body(matchService.endMatch(id));
    }

//    @ApiOperation(value = "Classificação")
//    @GetMapping("/standings")
//    public ResponseEntity<MatchDTO> standings(){
//        return ResponseEntity.ok().body(matchService.standings());
//    }



}
