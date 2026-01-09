package com.example.hth.repository;

import com.example.hth.domain.PlantType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlantTypeRepository {

    private final List<PlantType> plantTypes = new ArrayList<>();

    public PlantTypeRepository() {
        init();
    }

    // 테스트용 식물 타입 데이터
    private void init() {
        plantTypes.add(new PlantType(1L, "plant1.png", "새싹"));
        plantTypes.add(new PlantType(2L, "plant2.png", "작은 나무"));
    }

    public PlantType findById(Long id) {
        return plantTypes.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
