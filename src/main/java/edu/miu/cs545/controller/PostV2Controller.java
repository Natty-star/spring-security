package edu.miu.cs545.controller;

import edu.miu.cs545.domain.dto.PostDtoV2;
import edu.miu.cs545.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostV2Controller {
    @Autowired
    private PostService postService;

    @GetMapping(value = "/posts" , headers = "v=2")
    public List<PostDtoV2> getAllV2(){
        return postService.getAll2();
    }
}
