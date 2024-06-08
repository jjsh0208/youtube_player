package com.example.youtube_player.controller;


import com.example.youtube_player.service.YoutubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
@RequestMapping("/youtube")
public class YoutubeController {
    private final YoutubeService youtubeService;

    @GetMapping
    public String searchVideo(@RequestParam String keyword , Model model) throws IOException {
        // YoutubeService를 통해 동영상 검색한 결과를 받아옴
        // youtube?keyword= value 를 가져옴
        HashMap<String,String> result = youtubeService.searchVideo(keyword);
        if (result == null){
            return "redirect:/index";
        }
        model.addAttribute("youtubeInfo", result);
        return "index";
    }
}