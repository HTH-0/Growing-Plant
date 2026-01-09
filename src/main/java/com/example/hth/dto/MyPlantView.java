package com.example.hth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyPlantView {
    private Long id;           // userPlantId
    private String name;       // plantType.name
    private String img;        // plantType.img
    private int commitCount;   // userPlant.commitCount
    private String growthStage;// userPlant.growthStage
}
