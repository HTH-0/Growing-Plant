package com.example.hth.service;

import com.example.hth.domain.PlantType;
import com.example.hth.domain.UserPlant;
import com.example.hth.dto.MyPlantView;
import com.example.hth.repository.PlantTypeRepository;
import com.example.hth.repository.UserPlantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlantService {

    private final UserPlantRepository userPlantRepository;
    private final PlantTypeRepository plantTypeRepository;

    // (뷰용) 홈: 유저의 식물 목록 조회
    public List<MyPlantView> getMyPlantsView(String userId) {
        List<UserPlant> plants = userPlantRepository.findByUserId(userId);

        return plants.stream()
                .map(this::toView)
                .toList();
    }
    // (뷰용) 상세: 유저 식물 단건 조회
    public MyPlantView getMyPlantView(Long id) {
        UserPlant userPlant = userPlantRepository.findById(id);
        if (userPlant == null) return null;

        return toView(userPlant);
    }

    // (도메인용) 홈: 유저의 식물 목록 조회
    public List<UserPlant> getMyPlants(String userId) {
        return userPlantRepository.findByUserId(userId);
    }

    // (도메인용) 상세: 유저 식물 단건 조회
    public UserPlant getMyPlantById(Long id) {
        return userPlantRepository.findById(id);
    }

    // 성장 업데이트 요청(커밋 기반)
    public void growMyPlants(String userId) {
        int commitCount = fetchCommitCount(userId);

        List<UserPlant> plants = userPlantRepository.findByUserId(userId);
        for (UserPlant plant : plants) {
            plant.applyCommit(commitCount);
        }
    }

    // UserPlant + PlantType을 합쳐서 화면용 DTO로 변환
    private MyPlantView toView(UserPlant userPlant) {
        PlantType plantType = plantTypeRepository.findById(userPlant.getPlantTypeId());

        if (plantType == null) {
            return new MyPlantView(
                    userPlant.getId(),
                    "unknown",
                    "default.png",
                    userPlant.getCommitCount(),
                    userPlant.getGrowthStage()
            );
        }

        return new MyPlantView(
                userPlant.getId(),
                plantType.getName(),
                plantType.getImg(),
                userPlant.getCommitCount(),
                userPlant.getGrowthStage()
        );
    }

    // GitHub API 연동 자리(지금은 더미)
    private int fetchCommitCount(String userId) {
        return 42;
    }
}
