package com.example.hth.repository;

import com.example.hth.domain.UserPlant;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserPlantRepository {

    private final List<UserPlant> userPlantList = new ArrayList<>();

    public UserPlantRepository() {
        init();
    }

    // 테스트용 레파지토리. 임시 객체를 만든다.
    private void init() {
        // testUser에게 식물 2개 부여 예시 (원하면 1개로도 가능)
        userPlantList.add(new UserPlant(1L, "testUser", 1L, 12, "새싹"));
        userPlantList.add(new UserPlant(2L, "testUser", 2L, 35, "작은 나무"));
    }

    // 사용자의 모든 UserPlant 조회
    public List<UserPlant> findByUserId(String userId) {
        return userPlantList.stream()
                .filter(p -> p.getUserId().equals(userId))
                .toList();
    }

    // UserPlant의 id 기준으로 단건조회
    public UserPlant findById(Long id) {
        return userPlantList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // UserPlant 저장 (메모리 기반, 임시 구현)
    public void save(UserPlant userPlant) {
        // 메모리라 단순 추가(중복 id 방지는 나중에)
        userPlantList.add(userPlant);
    }
}
