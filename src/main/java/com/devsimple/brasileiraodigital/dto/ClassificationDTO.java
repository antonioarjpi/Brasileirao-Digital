package com.devsimple.brasileiraodigital.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ClassificationDTO {

    private List<StandingsDTO> teams = new ArrayList<>();
}
