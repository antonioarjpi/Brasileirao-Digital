package com.devsimple.brasileiraodigital.controller;

import com.devsimple.brasileiraodigital.services.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
@RequestMapping("/matches")
@AllArgsConstructor
public class MatchController {

    private MatchService matchService;

    @PostMapping
    public ResponseEntity<Void> generateMatch(){
        matchService.generateMatch(LocalDateTime.now(), Arrays.asList());
        return ResponseEntity.ok().build();
    }
}
