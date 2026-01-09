    package com.example.hth.controller;

    import com.example.hth.domain.UserPlant;
    import com.example.hth.dto.MyPlantView;
    import com.example.hth.service.PlantService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;

    import java.util.List;

    @Controller
    @RequiredArgsConstructor
    public class HomeController {

        private final PlantService plantService;

        @GetMapping("/")
        public String home(Model model){


            // 로그인 테스트용 변수
            String remoteUser = "testUser"; // null이면 로그인 전 화면
            model.addAttribute("remoteUser", remoteUser);

            // 더미 plantList
            List<MyPlantView> myPlants = plantService.getMyPlantsView(remoteUser);
            model.addAttribute("myPlants", myPlants);

            return "home";
        }

        // 성장 업데이트 요청(커밋 기반): 커밋 수 반영 -> UserPlant 상태 갱신
        @PostMapping("/my-plant/grow")
        public String growMyPlants(){
            String remoteUser = "testUser";
            plantService.growMyPlants(remoteUser);
            return "redirect:/";
        }

        // 단건조회 -> 식물 상세페이지 이동하기
        @GetMapping("/my-plant/{id}")
        public String plantDetail(@PathVariable Long id, Model model){

            // UserPlant를 id로 조회
            MyPlantView plant = plantService.getMyPlantView(id);

            // null 예외처리
            if (plant == null){

                return "redirect:/";
            }
            model.addAttribute("plant", plant);
            return "plant-detail";
        }
    }
