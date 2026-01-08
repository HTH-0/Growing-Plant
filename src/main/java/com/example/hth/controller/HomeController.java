package com.example.hth.controller;

import com.example.hth.domain.Plant;
import com.example.hth.store.PlantStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final PlantStore plantStore = PlantStore.getInstance();

    @GetMapping("/")
    public String home(Model model){


        // 로그인 테스트용 변수
        String remoteUser = "testUser"; // null이면 로그인 전 화면
        model.addAttribute("remoteUser", remoteUser);

        // 더미 plantList
        List<Plant> plantList = plantStore.getAll();
        model.addAttribute("plantList", plantList);

        return "home";
    }

    @GetMapping("/grow")
    public String grow(){
        plantStore.growAll();
        return "redirect:/";
    }
}
