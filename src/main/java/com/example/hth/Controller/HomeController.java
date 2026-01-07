package com.example.hth.Controller;

import com.example.hth.Domain.Plant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model){

        // 로그인 테스트용 변수
        String remoteUser = "testUser"; // null이면 로그인 전 화면
        model.addAttribute("remoteUser", remoteUser);

        System.out.println("HomeController 호출됨, remoteUser=" + remoteUser);
        // 더미 plantList
        List<Plant> plantList = new ArrayList<>();
        plantList.add(new Plant("plant1.png", "식물 1", 12, "새싹"));
        plantList.add(new Plant("plant2.png", "식물 2", 35, "작은 나무"));
        plantList.add(new Plant("plant3.png", "식물 3", 58, "나무"));
        plantList.add(new Plant("plant4.png", "식물 4", 80, "큰 나무"));

        model.addAttribute("plantList", plantList);

        return "home";
    }
}
